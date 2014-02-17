package com.amibee.vishu.scrapbook.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LoginCheckFilter implements Filter {

	public static final Logger log = Logger.getLogger(LoginCheckFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		String requestURI = httpReq.getRequestURI();
		Cookie[] cookies = httpReq.getCookies();
		if (cookies != null)
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = httpReq.getCookies()[i];
				if (cookie.getName().equals("JSESSIONID")) {
					log.debug(cookie.getValue());
					break;
				}
			}

		log.debug("request for " + httpReq.getRequestURI());
		if (requestURI.startsWith("/boto/post/create")) {
			if (httpReq.getSession().getAttribute("USERID") == null) {
				log.debug("User not logged in");
				log.debug("redirecting user to LOGIN");
				httpResponse.sendRedirect(req.getServletContext()
						.getContextPath() + "/login?returnTo=CREATE_POST");
				return;
			}
		} else if (requestURI.matches("^/boto/post/delete/\\d+$")) {
			if (httpReq.getSession().getAttribute("USERID") == null) {
				log.debug("User not logged in");
				log.debug("redirecting user to LOGIN");
				httpResponse.sendRedirect(req.getServletContext()
						.getContextPath()
						+ "/login?returnTo=DELETE_POST?"
						+ requestURI.split("/")[4]);
				return;
			}
		} else if (requestURI.matches("^/boto/post/edit/\\d+$")) {
			if (httpReq.getSession().getAttribute("USERID") == null) {
				log.debug("User not logged in");
				log.debug("redirecting user to LOGIN");
				httpResponse.sendRedirect(req.getServletContext()
						.getContextPath()
						+ "/login?returnTo=EDIT_POST?"
						+ requestURI.split("/")[4]);
				return;
			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
