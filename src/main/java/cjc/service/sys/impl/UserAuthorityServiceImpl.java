package cjc.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.common.utils.ListUtil;
import cjc.dto.MenuDTO;
import cjc.dto.UserDTO;
import cjc.entity.sys.Menu;
import cjc.entity.sys.Role;
import cjc.entity.sys.User;
import cjc.mapper.sys.UserAuthorityMapper;
import cjc.service.sys.UserAuthorityService;

@Service("userAuthorityService")
public class UserAuthorityServiceImpl implements  UserAuthorityService{

	@Autowired
	private UserAuthorityMapper	userAuthorityMapper;
	
	
	@Override
	public List<MenuDTO> getMenusByUserId(Integer userId) {
		List<Role> roles=userAuthorityMapper.getRolesByUserId(userId);
		if(!ListUtil.isEmpty(roles)){
			List<Menu> meuns=userAuthorityMapper.getMenusByRoleId(roles.get(0).getId());
			List<Menu> firstMeuns=userAuthorityMapper.getMenusByLevel(Menu.FIRST_LEVEL);
			List<MenuDTO> menuDTOs=ListUtil.newArrayList();
			for(Menu fmenu:firstMeuns){
				List<Menu> sMenus=ListUtil.newArrayList();
				for(Menu menu:meuns){
					if(menu.getParentId().equals(fmenu.getId())){
						sMenus.add(menu);
					}
				}
				if(!ListUtil.isEmpty(sMenus)){
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
		return ListUtil.newArrayList();
	}

	public User queryUsers(String username,String password){
		return userAuthorityMapper.queryUser(username, password);
	}

	@Override
	public UserDTO getUser(Integer userId) {
		UserDTO userDTO=new UserDTO();
		User user= userAuthorityMapper.getUser(userId);
		userDTO.setEmail(user.getEmail());
		userDTO.setNickName(user.getNickName());
		userDTO.setMobile(userDTO.getMobile());
		userDTO.setEmail(userDTO.getEmail());
		List<Role>  roles=userAuthorityMapper.getRolesByUserId(userId);
		userDTO.setRole(roles.get(0));
		return userDTO;
	}

	@Override
	public List<Menu> getMenusByLevel(Integer level) {
		return userAuthorityMapper.getMenusByLevel(level);
	}
}
