package cjc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.entity.sys.UserLogin;
import cjc.mapper.sys.UserLoginMapper;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	private UserLoginMapper userLoginMapper;
	
	@Override
	public List<UserLogin> findByUsernameAndPassword(String username,
			String password) {
		List<UserLogin> logins=userLoginMapper.findUserPass(username, password);
		return logins;
	}

}
