package cjc.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.dao.entity.sys.UserLogin;


public interface UserLoginService {
	
	List<UserLogin> findByUsernameAndPassword(String username,String password);

}
