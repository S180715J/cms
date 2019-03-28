package com.newer.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 
 * 牛耳教育,180801J: springboot 注册拦截器 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月20日
 */

@Configuration
public class LoginMvcConfig extends WebMvcConfigurationSupport {

	@Autowired
	private LoginInterceptor login;

	/**
	 * 注册拦截器
	 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(login).addPathPatterns("/**").excludePathPatterns("/login.html", "/login", "/");

		super.addInterceptors(registry);
	}

	/**
	 * 功能描述: 配置静态资源,避免静态资源请求被拦截
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/cmshtml/**").addResourceLocations("classpath:/cmshtml/**");

		super.addResourceHandlers(registry);
	}

}
