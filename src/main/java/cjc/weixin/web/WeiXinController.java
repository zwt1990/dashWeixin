package cjc.weixin.web;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cjc.controller.BaseController;
import cjc.controller.common.H5Response;
import cjc.dto.MenuButton;
import cjc.dto.QuestionDTO;
import cjc.entity.weixin.WeixinConfig;
import cjc.entity.weixin.AlbumConfig;
import cjc.service.exam.ExamService;
import cjc.service.weixin.WechatService;
import cjc.weixin.sdk.CustomMenu.Button;
import cjc.weixin.sdk.CustomMenu.CustomButton;
import cjc.weixin.sdk.CustomMenu.TYPE;
import cjc.weixin.sdk.WeiXinUtil;
import cjc.weixin.sdk.WeixinException;

import com.alibaba.fastjson.JSONObject;
@Controller
public class WeiXinController extends BaseController{

	@Autowired
	private WechatService wechatService;
	
	@Autowired
	private ExamService	examService;
	
	@RequestMapping("/weixin/updateBtn")
	@ResponseBody
	public JSONObject updateBtn(Integer configId,@RequestBody List<MenuButton> menuButtons,HttpServletRequest request) {
		JSONObject json=new JSONObject();
		if(CollectionUtils.isEmpty(menuButtons)){
			json.put("code", -2);
			json.put("msg","参数不正确");
			return json;
		}
		CustomButton customButton=new CustomButton();
		for(MenuButton menuButton: menuButtons){
			Button button=customButton.addButton(TYPE.view, menuButton.getName(), menuButton.getEventKey(), menuButton.getUrl());
			if(!CollectionUtils.isEmpty(menuButton.getSubButtons())){
				for(MenuButton subBtn: menuButton.getSubButtons()){
					button.addSubButton(TYPE.view, subBtn.getName(), subBtn.getEventKey(), subBtn.getUrl());
				}
			}
		}
		WeixinConfig config=wechatService.getWxConfig(configId);
		try{
			WeiXinUtil.updateMenu(config, customButton);
			json.put("code", 0);
			return json;
		}catch(WeixinException e){
			json.put("code", e.getErrcode());
			json.put("msg", e.getErrmsg());
			return json;
		}
		
	}
	
	@RequestMapping("/weixin/getConfigs")
	@ResponseBody
	public H5Response getConfigs() throws WeixinException {
		List<WeixinConfig> configs=wechatService.allWeixinConfigs();
		return succeed(configs);
	}
	
	@RequestMapping("/weixin/uploadImgs")
	@ResponseBody
	public H5Response uploadImgs(HttpServletRequest request, MultipartFile file,String url,Integer category,Integer configId) throws WeixinException, IOException {
		AlbumConfig imgConfig=wechatService.insertImgConfig(configId,category,url,"/static/img/upload/"+new Date().getTime() + new String(file.getOriginalFilename().getBytes(),"UTF-8"));
		 //拿到输出流，同时重命名上传的文件  
		String filePath = request.getSession().getServletContext()
                .getRealPath("/") +"sys/"+imgConfig.getPath() ;
         FileOutputStream os = new FileOutputStream(filePath);  
         //拿到上传文件的输入流  
         FileInputStream in = (FileInputStream) file.getInputStream();  
         //以写字节的方式写文件  
         int b = 0;  
         while((b=in.read()) != -1){  
             os.write(b);  
         }  
         os.flush();  
         os.close();  
         in.close();  
         return succeed();
	}
	
	@RequestMapping("/weixin/getImgconfigs")
	@ResponseBody
	public JSONObject getImgconfigs(Integer configId,Integer category ) throws WeixinException {
			JSONObject json=new JSONObject();
			List<AlbumConfig>  wxImgconfigs=wechatService.getUsefulImgs(configId,category);
			if(category!=null){
				json.put("imgs", wxImgconfigs);
				return json;
			}
			List<AlbumConfig> cslImgs=new ArrayList<AlbumConfig>();
			List<AlbumConfig> menuImgs=new ArrayList<AlbumConfig>();
			List<AlbumConfig> detailImgs=new ArrayList<AlbumConfig>();
			for(AlbumConfig img:wxImgconfigs){
				if(img.getCategory()==1){
					cslImgs.add(img);
				}
				if(img.getCategory()==2){
					menuImgs.add(img);
				}
				if(img.getCategory()==3){
					detailImgs.add(img);
				}
			}
			json.put("cslImgs", cslImgs);
			json.put("menuImgs", menuImgs);
			json.put("detailImgs", detailImgs);
			return json;
			
	}
}
