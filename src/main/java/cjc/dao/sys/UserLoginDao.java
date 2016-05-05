package cjc.dao.sys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.dao.entity.sys.UserLogin;


public interface UserLoginDao  extends CrudRepository<UserLogin, Integer>{
	
	List<UserLogin> findByUsernameAndPassword(String username,String password);

}
