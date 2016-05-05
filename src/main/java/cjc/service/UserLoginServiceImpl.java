package cjc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.dao.entity.sys.UserLogin;
import cjc.dao.sys.UserLoginDao;
import cjc.mybatis.mapper.UserLoginMapper;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	private  UserLoginDao userLoginDao;
	@Autowired
	private UserLoginMapper userLoginMapper;
	
	@Override
	public List<UserLogin> findByUsernameAndPassword(String username,
			String password) {
		UserLogin login=userLoginMapper.queryById(1);
		System.out.println(login.getPassword());
		return userLoginDao.findByUsernameAndPassword(username, password);
	}

}
