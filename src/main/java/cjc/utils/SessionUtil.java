package cjc.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	private  static HttpSession session=null;
	
	 private SessionUtil(){
		 
	 }
	
	public static  HttpSession getIntance(HttpServletRequest request){
		if(session==null){
			session=request.getSession();
		}
		return session;
	}
	
	public static Object getValue(String key){
		return session.getAttribute(key);
	}
}
