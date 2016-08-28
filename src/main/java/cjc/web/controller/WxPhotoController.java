package cjc.web.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cjc.common.weixin.sdk.WeixinException;
import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.PhotoExt;
import cjc.service.weixin.WxPhotoService;
import cjc.web.controller.common.H5Response;

import com.alibaba.fastjson.JSONObject;
@Controller
public class WxPhotoController extends BaseController{
	@Autowired
	private WxPhotoService wxPhotoService;
	
	@RequestMapping("/wxphoto/uploadImgs")
	@ResponseBody
	public H5Response uploadImgs(HttpServletRequest request, MultipartFile file,Integer category,Integer configId,String name) throws WeixinException, IOException {
		PhotoConfig imgConfig=wxPhotoService.insertImgConfig(configId,category,"/img/upload/"+new Date().getTime() + new String(file.getOriginalFilename().getBytes(),"UTF-8"),name);
		 //拿到输出流，同时重命名上传的文件  
		String baseUrl= request.getSession().getServletContext()
                .getRealPath("/")+"sys/static";//TODO 后面可修改nigux映射到其他盘
		String filePath =baseUrl+imgConfig.getPath() ;
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
	
	@RequestMapping("/wxphoto/getImgconfigs")
	@ResponseBody
	public JSONObject getImgconfigs(Integer configId,Integer category ) throws WeixinException {
			JSONObject json=new JSONObject();
			List<PhotoConfig>  wxImgconfigs=wxPhotoService.getUsefulImgs(configId,category);
			if(category!=null){
				json.put("imgs", wxImgconfigs);
				return json;
			}
			List<PhotoConfig> cslImgs=new ArrayList<PhotoConfig>();
			List<PhotoConfig> menuImgs=new ArrayList<PhotoConfig>();
			List<PhotoConfig> detailImgs=new ArrayList<PhotoConfig>();
			for(PhotoConfig img:wxImgconfigs){
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
	
	@RequestMapping("/wxphoto/getPhotoByConfigId")
	@ResponseBody
	public H5Response getPhotoByConfigId(Integer wxconfig){
		List<PhotoConfig>  wxImgconfigs=wxPhotoService.getUsefulImgs(wxconfig,null);
		return succeed(wxImgconfigs);
	}
	
	@RequestMapping("/wxphoto/addPhotoExt")
	@ResponseBody
	public H5Response addPhotoExt(Integer pConfigId,String key,String value,Integer type){
		wxPhotoService.addPhotoExt(pConfigId, key, value, type);
		return succeed();
	}
	@RequestMapping("/wxphoto/getPhotoExts")
	@ResponseBody
	public H5Response addPhotoExts(Integer pConfigId){
		List<PhotoExt> photoExts=wxPhotoService.findByPConfigId(pConfigId);
		return succeed(photoExts);
	}
	
	@RequestMapping("/wxphoto/deletePhoto")
	@ResponseBody
	public H5Response deletePhoto(Integer id){
		wxPhotoService.deleteImgConfig(id);
		return succeed();
	}
	
	@RequestMapping("/wxphoto/deletePhotoExt")
	@ResponseBody
	public H5Response deletePhotoExt(Integer id){
		wxPhotoService.deletePhotoExt(id);
		return succeed();
	}
	
	@RequestMapping("/wxphoto/addImgExt")
	@ResponseBody
	public H5Response addImgExt(HttpServletRequest request, MultipartFile file,Integer pconfigId,Integer type) throws WeixinException, IOException {
		String path="/img/upload/"+new Date().getTime() + new String(file.getOriginalFilename().getBytes(),"UTF-8");
		String key="轮播图片";
		if(type==3){
			key="详情图片";
		}
		wxPhotoService.addPhotoExt(pconfigId, key, path, type);
		 //拿到输出流，同时重命名上传的文件  
		String baseUrl= request.getSession().getServletContext()
                .getRealPath("/")+"sys/static";
		String filePath =baseUrl+path ;
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
	
	@RequestMapping("/wxphoto/getTypePhotoExt")
	@ResponseBody
	public JSONObject getTypePhotoExt(Integer pConfigId){
		List<PhotoExt> photoExts=wxPhotoService.findByPConfigId(pConfigId);
		List<PhotoExt> sliderPhotoExts=new ArrayList<PhotoExt>();
		List<PhotoExt> formDataExts=new ArrayList<PhotoExt>();
		List<PhotoExt> descImgExts=new ArrayList<PhotoExt>();
		for(PhotoExt photoExt:photoExts){
			if(photoExt.getType()==1){//轮播
				sliderPhotoExts.add(photoExt);
			}
			if(photoExt.getType()==2){
				formDataExts.add(photoExt);
			}
			if(photoExt.getType()==3){
				descImgExts.add(photoExt);
			}
		}
		JSONObject json=new JSONObject();
		json.put("sliderExts", sliderPhotoExts);
		json.put("formExts", formDataExts);
		json.put("descImgExts", descImgExts);
		return json;
	}
}
