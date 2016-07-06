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
import cjc.dto.MenuDTO;
import cjc.entity.sys.User;
import cjc.service.sys.UserAuthorityService;
import cjc.utils.Md5Util;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="sys/")
public class UserLoginController extends BaseController{

	@Autowired
	private UserAuthorityService	userAuthorityService;
	
	
	@RequestMapping(value = "login")
	@ResponseBody
	public JSONObject queryList(HttpServletRequest request,
			HttpServletResponse response,String username,String password) throws IOException {
		String Md5pass=Md5Util.getMD5String(password);
		User user=userAuthorityService.queryUsers(username, Md5pass);
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("code", -2);
			json.put("msg", "用户名或密码不正确");
			 return json;
		}
		json.put("code", 0);
		return json;
		
	}
	
	@RequestMapping(value = "getMainInfo")
	@ResponseBody
	public H5Response getMainInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		Integer userId=(Integer) session.getAttribute("userId");
		userId=1;
		User user=userAuthorityService.getUser(userId);
		List<MenuDTO> muens=userAuthorityService.getMenusByUserId(userId);
		JSONObject json=new JSONObject();
		json.put("code",0);
		json.put("user", user);
		json.put("menu", muens);
		return succeed(json);
	}
}
