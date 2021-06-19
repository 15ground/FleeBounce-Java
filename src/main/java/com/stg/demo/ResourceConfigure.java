package com.stg.demo;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ResourceConfigure {
	@Bean("messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource mSource = new ReloadableResourceBundleMessageSource();
		mSource.setBasenames("classpath:message/error", "classpath:i18n/index");
		mSource.setDefaultEncoding("utf-8");
		return mSource;
	}
}
