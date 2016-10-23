package cjc.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cjc.common.utils.FileUtil;
import cjc.common.utils.FileUtil.FileSie;
import cjc.common.utils.SessionUtil;
import cjc.common.weixin.sdk.WeixinException;
import cjc.dto.WeixinPhotoDTO;
import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.PhotoExt;
import cjc.service.weixin.WxPhotoService;
import cjc.web.controller.common.H5Response;

import com.alibaba.fastjson.JSONObject;
@Controller
public class WxPhotoController extends BaseController{
	@Autowired
	private WxPhotoService wxPhotoService;
	
	
  ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();  
	
  
	@RequestMapping("/wxphoto/getPhotos")
	@ResponseBody
	public H5Response getPhotos(HttpServletRequest request) throws  IOException {
		Integer userId = (Integer) SessionUtil.getUserId(request);
		if(userId==null){
			return failed("用户尚未登录");
		}
		 List<WeixinPhotoDTO> weixinPhotoDTOs= wxPhotoService.getWeixinPhotos(userId);
		 return succeed(weixinPhotoDTOs);
	}
  
  
	@RequestMapping("/wxphoto/uploadImgs")
	@ResponseBody
	public H5Response uploadImgs(HttpServletRequest request,final  MultipartFile file,Integer category,Integer configId,String name,String price) throws WeixinException, IOException {
		String absPath="/img/upload/"+new Date().getTime() +Math.random()*9000+1000;
		final String filePath =request.getSession().getServletContext()
                .getRealPath("/")+"sys/static"+absPath;
		wxPhotoService.insertImgConfig(configId,category,absPath,name,price);
		final  FileInputStream in=(FileInputStream) file.getInputStream();
		singleThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				FileUtil.uploadCompressPic(in,filePath, true,FileSie.small);
			}
		});
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
	public H5Response deletePhoto(HttpServletRequest request,Integer id){
		wxPhotoService.deleteImgConfig(request,id);
		return succeed();
	}
	
	@RequestMapping("/wxphoto/deletePhotoExt")
	@ResponseBody
	public H5Response deletePhotoExt(HttpServletRequest request,Integer id){
		wxPhotoService.deletePhotoExt(request,id);
		return succeed();
	}
	
	@RequestMapping("/wxphoto/addImgExt")
	@ResponseBody
	public H5Response addImgExt(HttpServletRequest request,final  MultipartFile file,Integer pconfigId,Integer type) throws WeixinException, IOException {
		String key="轮播图片";
		if(type==3){
			key="详情图片";
		}
		String absPath="/img/upload/"+new Date().getTime() +Math.random()*9000+1000;
		final String filePath =request.getSession().getServletContext()
                .getRealPath("/")+"sys/static"+absPath;
		wxPhotoService.addPhotoExt(pconfigId, key, absPath, type);
		final  FileInputStream in=(FileInputStream) file.getInputStream();
		singleThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				FileUtil.uploadCompressPic(in,filePath, true,FileSie.middle);
			}
		});
         return succeed();
	}
	
	@RequestMapping("/wxphoto/getTypePhotoExt")
	@ResponseBody
	public JSONObject getTypePhotoExt(Integer pConfigId){
		PhotoConfig photoConfig=wxPhotoService.get(pConfigId);
		List<PhotoExt> photoExts=wxPhotoService.findByPConfigId(pConfigId);
		List<PhotoExt> sliderPhotoExts=new ArrayList<PhotoExt>();
		List<PhotoExt> formDataExts=new ArrayList<PhotoExt>();
		List<PhotoExt> descImgExts=new ArrayList<PhotoExt>();
		PhotoExt priceExt=new PhotoExt();
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
			if(photoExt.getType()==4){
				priceExt=photoExt;
			}	
		}
		JSONObject json=new JSONObject();
		json.put("sliderExts", sliderPhotoExts);
		json.put("formExts", formDataExts);
		json.put("priceExt", priceExt);
		json.put("descImgExts", descImgExts);
		json.put("name", photoConfig.getName());
		return json;
	}
}
