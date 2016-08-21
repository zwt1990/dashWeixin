package cjc.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.dto.MenuDTO;
import cjc.dto.UserDTO;
import cjc.entity.sys.User;
import cjc.service.sys.UserAuthorityService;
import cjc.web.controller.common.H5Response;
import cjc.common.utils.Md5Util;
import cjc.common.utils.SessionUtil;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="manange/")
public class ManangeController extends BaseController{

	@Autowired
	private UserAuthorityService	userAuthorityService;
	
	@RequestMapping(value = "getMainInfo")
	@ResponseBody
	public H5Response getMainInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer userId=(Integer) SessionUtil.getUserId(request);
		UserDTO user=userAuthorityService.getUser(userId);
		List<MenuDTO> muens=userAuthorityService.getMenusByUserId(userId);
		JSONObject json=new JSONObject();
		json.put("user", user);
		json.put("menu", muens);
		return succeed(json);
	}
	
	@RequestMapping(value = "/getShowPage")
	@ResponseBody
	public H5Response getShowPage(HttpServletRequest request,
			HttpServletResponse response,String username,String url) throws IOException {
		return succeed(url);
		
	}
	
	
}
