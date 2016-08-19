package cjc.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	private  static HttpSession session=null;
	
	 private SessionUtil(){
		 
	 }
	
	public  static HttpSession getIntance(HttpServletRequest request){
		if(session==null){
			session=request.getSession();
			session.setMaxInactiveInterval(60*60*2);
		}
		return session;
	}
	public static void login(HttpServletRequest request,Integer userId){
		getIntance( request).setAttribute("userId", userId);
	}
	public static void logout(HttpServletRequest request){
		getIntance( request).removeAttribute("userId");
	}
	
	public static Object getValue(HttpServletRequest request,String key){
		return getIntance( request).getAttribute(key);
	}
	public static Object getUserId(HttpServletRequest request){
		return getIntance( request).getAttribute("userId");
	}
}
