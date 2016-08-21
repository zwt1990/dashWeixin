package cjc.service.sys;

import java.util.List;

import cjc.dto.MenuDTO;
import cjc.dto.UserDTO;
import cjc.entity.sys.Menu;
import cjc.entity.sys.User;


public interface UserAuthorityService {
	
	public UserDTO getUser(Integer userId);
	
	public User queryUsers(String username,String password);
	
	public List<MenuDTO> getMenusByUserId(Integer userId);
	
	List<Menu> getMenusByLevel( Integer level);
}
