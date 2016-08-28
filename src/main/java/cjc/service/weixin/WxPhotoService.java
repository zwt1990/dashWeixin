package cjc.service.weixin;

import java.util.List;

import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.PhotoExt;

public interface WxPhotoService {
	
	public PhotoConfig insertImgConfig(Integer wxConfigId,Integer category,String path,String name);
	
	public void deleteImgConfig(Integer id);
	
	public void deletePhotoExt(Integer id);
	
	public List<PhotoConfig> getUsefulImgs(Integer configId,Integer category);
	
	public List<PhotoExt> findByPConfigId(Integer pconfigId);
	
	public void addPhotoExt(Integer pConfigId,String key,String value,Integer type);
}
