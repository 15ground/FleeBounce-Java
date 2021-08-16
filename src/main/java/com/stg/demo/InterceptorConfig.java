package com.stg.demo;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import com.stg.demo.interceptor.CartInterceptor;
import com.stg.demo.interceptor.UserInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	CartInterceptor cartInterceptor;
	@Autowired
	UserInterceptor userInterceptor;

	@Bean("localeResolver")
	public LocaleResolver getLocaleResolver() {
		CookieLocaleResolver clr = new CookieLocaleResolver();
		clr.setDefaultLocale(new Locale("vi"));
		clr.setCookiePath("/");
		clr.setCookieMaxAge(60 * 60);
		return clr;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(cartInterceptor).addPathPatterns("/check-out", "/your-cart");
		registry.addInterceptor(userInterceptor).addPathPatterns("/products/list", "/category/*", "/order/*",
				"/dashboard");
		// set da ngon ngu
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		registry.addInterceptor(lci);
	}
}
