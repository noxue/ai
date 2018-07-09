package com.ai;

import com.ai.shiro.filter.AppFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ai.support.XssSqlStringJsonSerializer;
import org.mybatis.spring.annotation.MapperScan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;


@SpringBootApplication
@MapperScan("com.ai.dao")
@EnableCaching
@ServletComponentScan
public class AiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiApplication.class, args);
	}

	@Bean
	@Primary
	public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
		// 解析器
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		// 注册XSS SQL 解析器
		SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
		xssModule.addSerializer(new XssSqlStringJsonSerializer());
		objectMapper.registerModule(xssModule);
		return objectMapper;
	}


	/**
	 * 配置过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(appFilter());
		registration.addUrlPatterns("/robot/api/*");
//		registration.addInitParameter("paramName", "paramValue");
//		registration.setName("appFilter");
		return registration;
	}

	/**
	 * 创建一个bean
	 * @return
	 */
	@Bean(name = "appFilter")
	public Filter appFilter() {
		return new AppFilter();
	}


}
