/*package cjc.handler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

@WebFilter(filterName="webFilter",urlPatterns="/sys/*")
public class WebResouceFilter implements Filter {

	@Override
	public void destroy() {
		 System.out.println("过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			System.out.println("进来过滤器---");
			HttpServletRequest req= (HttpServletRequest) request;
			HttpServletResponse rsp= (HttpServletResponse) response;
			req.setCharacterEncoding("UTF-8");
//			if(req.getRequestURI().contains(".html")){
//				HttpSession session= req.getSession();
//				String userId=(String) session.getAttribute("userId");
//				if(StringUtils.isEmpty(userId)){
//					rsp.sendRedirect(req.getContextPath()+"/login.html");
//						 return ;
//					}
//				chain.doFilter(request, response);
//			}
			chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		  System.out.println("过滤器创建");
	}


}
*/