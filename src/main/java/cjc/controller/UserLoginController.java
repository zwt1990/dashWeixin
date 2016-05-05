package cjc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cjc.dao.entity.sys.UserLogin;
import cjc.service.UserLoginService;

@Controller
@RequestMapping(value="user/")
public class UserLoginController {

	@Autowired
	private UserLoginService	userLoginService;
	
	@RequestMapping(value = "login")
	public void queryList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String username="zhangsan";
		String password="2222";
		List<UserLogin> logins=userLoginService.findByUsernameAndPassword(username, password);
		if(logins==null||logins.size()==0){
			System.out.println("登录失败-----");
			 response.getWriter().print("fail");
			 return ;
		}
		System.out.println("登录成功-----");
		 response.getWriter().print("success");
	}
	
}
