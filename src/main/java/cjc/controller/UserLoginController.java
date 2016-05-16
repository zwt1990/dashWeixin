package cjc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.controller.common.H5Response;
import cjc.entity.sys.UserLogin;
import cjc.service.UserLoginService;

@Controller
@RequestMapping(value="user/")
public class UserLoginController extends BaseController{

	@Autowired
	private UserLoginService	userLoginService;
	
	@RequestMapping(value = "login")
	@ResponseBody
	public H5Response queryList(HttpServletRequest request,
			HttpServletResponse response,UserLogin userLogin) throws IOException {
		List<UserLogin> logins=userLoginService.findByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
		if(logins==null||logins.size()==0){
			 return failed("用户名或密码不正确");
		}
		HttpSession session=request.getSession();
		session.setAttribute("userId",logins.get(0).getId());
		 return succeed("登录成功");
	}
	
	@RequestMapping(value = "getAllUsers")
	@ResponseBody
	public H5Response getAllUsers(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<UserLogin> logins=userLoginService.getAllUsers();
		return succeed(logins);
	}
}
