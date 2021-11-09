package com.douzone.jblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;
import com.douzone.jblog.interceptor.BlogAdminInterceptor;

@Configuration //설정파일을 만들기 위한 애노테이션 or Bean을 등록하기 위한 애노테이션이다.
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.jblog.controller", "com.douzone.jblog.exception"})
@Import({MvcConfig.class, MessageConfig.class, FileUploadConfig.class, SecurityConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public HandlerInterceptor blogAdminInterceptor() {
		return new BlogAdminInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(blogAdminInterceptor()).addPathPatterns("/blog/admin/**");

	}
}
