package cjc.service.weixin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cjc.dto.WeixinPhotoDTO;
import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.PhotoExt;

public interface WxPhotoService {
	
	public PhotoConfig insertImgConfig(Integer wxConfigId,Integer category,String path,String name,String price);
	
	public void deleteImgConfig(HttpServletRequest request,Integer id);
	
	public void deletePhotoExt(HttpServletRequest request,Integer id);
	
	public List<PhotoConfig> getUsefulImgs(Integer configId,Integer category);
	
	public List<PhotoExt> findByPConfigId(Integer pconfigId);
	
	public void addPhotoExt(Integer pConfigId,String key,String value,Integer type);
	
	public PhotoConfig get(Integer id);
	
	public List<WeixinPhotoDTO> getWeixinPhotos(Integer userId);
	
}
