package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforActionInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private BeforActionInterceptor beforActionInterceptor;

	public WebConfig(BeforActionInterceptor beforActionInterceptor) {
		this.beforActionInterceptor = beforActionInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");
	}
}
