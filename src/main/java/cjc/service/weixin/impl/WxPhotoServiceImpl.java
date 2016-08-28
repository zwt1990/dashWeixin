package cjc.service.weixin.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.PhotoExt;
import cjc.mapper.weixin.PhotoExtDao;
import cjc.mapper.weixin.WeixinPhotoConfigDao;
import cjc.service.weixin.WxPhotoService;
@Service("wxPhotoService")
public class WxPhotoServiceImpl implements WxPhotoService{
	@Autowired
	private WeixinPhotoConfigDao	weixinImgConfigDao;
	@Autowired
	private PhotoExtDao	photoExtDao;
	@Override
	public void deleteImgConfig(Integer id) {
		List<PhotoExt> photos=photoExtDao.findByPConfigId(id);
		for(PhotoExt pr:photos){
			removeFile("../sys/static"+pr.getExtValue());
		}
		photoExtDao.delete(photos);
		PhotoConfig  photoConfig= weixinImgConfigDao.findOne(id);
		removeFile("../sys/static"+photoConfig.getPath());
		weixinImgConfigDao.delete(photoConfig);
	}

	@Override
	public List<PhotoConfig> getUsefulImgs(Integer configId,Integer category) {
		if(category!=null){
			return weixinImgConfigDao.findByConfigIdAndStatusAndCategory(configId, 1,category);
		}
		return weixinImgConfigDao.findByConfigIdAndStatus(configId, 1);
	}
	

	public PhotoConfig insertImgConfig(Integer wxConfigId,Integer category,String path,String name) {
		PhotoConfig imgConfig=new PhotoConfig();
		imgConfig.setCategory(category);
		imgConfig.setConfigId(wxConfigId);
		imgConfig.setPath(path);
		//imgConfig.setUrl(url);
		imgConfig.setCreateTime(new Date());
		imgConfig.setStatus(1);
		imgConfig.setName(name);
		return weixinImgConfigDao.save(imgConfig);
		
	}

	@Override
	public List<PhotoExt> findByPConfigId(Integer pconfigId) {
		return photoExtDao.findByPConfigId(pconfigId);
	}

	@Override
	public void addPhotoExt(Integer pConfigId, String key, String value,
			Integer type) {
			PhotoExt photoExt=new PhotoExt();
			photoExt.setExtKey(key);
			photoExt.setExtValue(value);
			photoExt.setType(type);
			photoExt.setpConfigId(pConfigId);
			photoExtDao.save(photoExt);
	}
	
	private void removeFile(String url){
		File file=new File(url);
		if(file.exists()){
			System.out.println("删除图片"+file.getName());
			file.delete();
		}
	}

	@Override
	public void deletePhotoExt(Integer id) {
		PhotoExt photoExt= photoExtDao.findOne(id);
		removeFile("../sys/static"+photoExt.getExtValue());
		photoExtDao.delete(photoExt);
	}
}
