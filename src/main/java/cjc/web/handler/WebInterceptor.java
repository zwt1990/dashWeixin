package cjc.web.handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cjc.common.utils.HttpUtils;
import cjc.common.utils.SessionUtil;

import com.alibaba.fastjson.JSONObject;
public class WebInterceptor  implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response,
			Object arg2) throws Exception {
		Integer userId = (Integer) SessionUtil.getUserId(req);
		if (userId != null) {
			return true;
		}
		if (HttpUtils.isAjax(req)) {
			JSONObject json=new JSONObject();
			json.put("status", false);
			json.put("code", -200);
			json.put("msg", "用户尚未登录");
			PrintWriter out = response.getWriter();
			out.print(json);// session失效
			out.flush();
			return false;
		}
		response.sendRedirect("login.html");
		return false;
	}

	
	
}
