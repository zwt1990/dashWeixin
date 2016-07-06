package cjc.service.sys.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.dto.MenuDTO;
import cjc.entity.sys.Menu;
import cjc.entity.sys.Role;
import cjc.entity.sys.User;
import cjc.mapper.sys.UserAuthorityMapper;
import cjc.service.sys.UserAuthorityService;

import com.google.common.collect.Lists;

@Service("userAuthorityService")
public class UserAuthorityServiceImpl implements  UserAuthorityService{

	@Autowired
	private UserAuthorityMapper	userAuthorityMapper;
	
	
	@Override
	public List<MenuDTO> getMenusByUserId(Integer userId) {
		List<Role> roles=userAuthorityMapper.getRolesByUserId(userId);
		if(!CollectionUtils.isEmpty(roles)){
			List<Menu> meuns=userAuthorityMapper.getMenusByRoleId(roles.get(0).getId());
			List<Menu> firstMeuns=userAuthorityMapper.getMenusByLevel(Menu.FIRST_LEVEL);
			List<MenuDTO> menuDTOs=Lists.newArrayList();
			for(Menu fmenu:firstMeuns){
				List<Menu> sMenus=Lists.newArrayList();
				for(Menu menu:meuns){
					if(menu.getParentId().equals(fmenu.getId())){
						sMenus.add(menu);
					}
				}
				if(!CollectionUtils.isEmpty(sMenus)){
					MenuDTO menuDTO=new MenuDTO();
					menuDTO.setId(fmenu.getId());
					menuDTO.setName(fmenu.getName());
					menuDTO.setUrl(fmenu.getUrl());
					menuDTO.setMenus(sMenus);
					menuDTOs.add(menuDTO);
				}
			}
			return menuDTOs;
			
		}
		return Lists.newArrayList();
	}

	public User queryUsers(String username,String password){
		return userAuthorityMapper.queryUser(username, password);
	}

	@Override
	public User getUser(Integer userId) {
		return userAuthorityMapper.getUser(userId);
	}

	@Override
	public List<Menu> getMenusByLevel(Integer level) {
		return userAuthorityMapper.getMenusByLevel(level);
	}
}
