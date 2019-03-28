package com.newer.cms.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.newer.cms.utils.JwtTokenUtil;

import io.jsonwebtoken.Claims;

/**
 * 
 * 牛耳教育,180801J: 解析jwt(token)拦截类 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月20日
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Value("${jwt.header}")
	private String heander;

	/**
	 * 目标方法执行之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求头
		String requestURI = request.getRequestURI();

		String header = request.getHeader(heander);

		try {
			Claims parseJWT = new JwtTokenUtil().parseJWT(header);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
