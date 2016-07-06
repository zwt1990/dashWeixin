package cjc.service.sys;

import java.util.List;

import cjc.dto.MenuDTO;
import cjc.entity.sys.Menu;
import cjc.entity.sys.User;


public interface UserAuthorityService {
	
	public User getUser(Integer userId);
	
	public User queryUsers(String username,String password);
	
	public List<MenuDTO> getMenusByUserId(Integer userId);
	
	List<Menu> getMenusByLevel( Integer level);
}
