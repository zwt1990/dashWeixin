package cjc.mybatis.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cjc.dao.entity.sys.UserLogin;

@Component
public class UserLoginMapper {
	
	   @Autowired
	    private SqlSessionTemplate sqlSessionTemplate;

	    public UserLogin queryById(int id) {
	        return this.sqlSessionTemplate.selectOne("queryById", id);
	    }
}
