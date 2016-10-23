package cjc.service.weixin.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cjc.dto.WeixinPhotoDTO;
import cjc.entity.sys.Role;
import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.PhotoExt;
import cjc.mapper.sys.UserAuthorityMapper;
import cjc.mapper.weixin.PhotoExtDao;
import cjc.mapper.weixin.WeixinPhotoConfigDao;
import cjc.service.weixin.WxPhotoService;
@Service("wxPhotoService")
public class WxPhotoServiceImpl implements WxPhotoService{
	@Autowired
	private WeixinPhotoConfigDao	weixinImgConfigDao;
	@Autowired
	private PhotoExtDao	photoExtDao;
	@Autowired
	private UserAuthorityMapper userAuthorityMapper;
	@Override
	public void deleteImgConfig(HttpServletRequest request ,Integer id) {
		List<PhotoExt> photos=photoExtDao.findByPConfigId(id);
		for(PhotoExt pr:photos){
			String filePath =request.getSession().getServletContext()
	                .getRealPath("/")+"sys/static"+pr.getExtValue();
			removeFile(filePath);
		}
		photoExtDao.delete(photos);
		PhotoConfig  photoConfig= weixinImgConfigDao.findOne(id);
		String secPath =request.getSession().getServletContext()
                .getRealPath("/")+"sys/static"+photoConfig.getPath();
		removeFile(secPath);
		weixinImgConfigDao.delete(photoConfig);
	}

	@Override
	public List<PhotoConfig> getUsefulImgs(Integer configId,Integer category) {
		if(category!=null){
			return weixinImgConfigDao.findByConfigIdAndStatusAndCategory(configId, 1,category);
		}
		return weixinImgConfigDao.findByConfigIdAndStatus(configId, 1);
	}
	

	public PhotoConfig insertImgConfig(Integer wxConfigId,Integer category,String path,String name,String price) {
		PhotoConfig imgConfig=new PhotoConfig();
		imgConfig.setCategory(category);
		imgConfig.setConfigId(wxConfigId);
		imgConfig.setPath(path);
		imgConfig.setCreateTime(new Date());
		imgConfig.setStatus(1);
		imgConfig.setName(name);
		PhotoConfig photoConfig= weixinImgConfigDao.save(imgConfig);
		if(!StringUtils.isEmpty(price)){
			addPhotoExt( photoConfig.getId(), "售价", price,
					4) ;
		}
		return photoConfig;
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
	public void deletePhotoExt(HttpServletRequest request,Integer id) {
		PhotoExt photoExt= photoExtDao.findOne(id);
		String filePath =request.getSession().getServletContext()
                .getRealPath("/")+"sys/static"+photoExt.getExtValue();
		removeFile(filePath);
		photoExtDao.delete(photoExt);
	}

	@Override
	public PhotoConfig get(Integer id) {
		return weixinImgConfigDao.findOne(id);
	}

	@Override
	public List<WeixinPhotoDTO> getWeixinPhotos(Integer userId) {
		List<Role> roles=userAuthorityMapper.getRolesByUserId(userId);
		List<WeixinPhotoDTO> photos=userAuthorityMapper.getWxPhotosByRoleId(roles.get(0).getId());
		return photos;
	}
}
