package cjc.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.common.utils.Md5Util;
import cjc.common.utils.SessionUtil;
import cjc.controller.common.H5Response;
import cjc.entity.sys.User;
import cjc.service.sys.UserAuthorityService;

@Controller
@RequestMapping(value="sys/")
public class UserLoginController extends BaseController{

	@Autowired
	private UserAuthorityService	userAuthorityService;
	
	
	@RequestMapping(value = "login")
	@ResponseBody
	public H5Response login(HttpServletRequest request,
			HttpServletResponse response,String username,String password) throws IOException {
		String Md5pass=Md5Util.getMD5String(password);
		User user=userAuthorityService.queryUsers(username, Md5pass);
		if(user==null){
			return failed("用户名或者密码不正确");
		}
		SessionUtil.login(request, user.getId());
		return succeed();
		
	}
	
	@RequestMapping(value = "logout")
	public String  queryList(HttpServletRequest request,
			HttpServletResponse response,String username) throws IOException {
		SessionUtil.logout(request);
		return "login.html";
		
	}
	
}
