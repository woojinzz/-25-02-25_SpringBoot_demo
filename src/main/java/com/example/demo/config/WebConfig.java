package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforActionInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private BeforActionInterceptor beforActionInterceptor;
	private NeedLoginInterceptor needLoginInterceptor;

	public WebConfig(BeforActionInterceptor beforActionInterceptor, NeedLoginInterceptor needLoginInterceptor) {
		this.beforActionInterceptor = beforActionInterceptor;
		this.needLoginInterceptor = needLoginInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");

		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/write")
				.addPathPatterns("/usr/article/doWrite").addPathPatterns("/usr/article/modify")
				.addPathPatterns("/usr/article/doModify").addPathPatterns("/usr/article/doDelete")
				.addPathPatterns("/usr/member/doLogout");

	}
}
