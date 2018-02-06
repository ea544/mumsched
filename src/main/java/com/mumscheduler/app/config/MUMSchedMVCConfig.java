package com.mumscheduler.app.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MUMSchedMVCConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	/**
	 * In order for our application to be able to determine which locale 
	 * is currently being used, we need to add a LocaleResolver bean:
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.US);
	    return slr;
	}
	
	/**
	 * Next, we need to add an interceptor bean that will switch to a new locale based on 
	 * the value of the lang parameter appended to a request:
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	/**
	 * In order to take effect, this bean needs to be added to 
	 * the application’s interceptor registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = 
	                                               new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames("classpath:i18n/entitylabels/formfields", 
	                               "classpath:i18n/sitelabels/sitelabels",
	                               "classpath:i18n/validation/validation",
	                               "classpath:i18n/validation/block");
	    messageSource.setCacheSeconds(0); 
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
//	@Bean
//	public MessageSource validationMessageSource() {
//	    ReloadableResourceBundleMessageSource messageSource = 
//	                                               new ReloadableResourceBundleMessageSource();
//	    messageSource.setBasename("classpath:/messages/validation/validation.properties");
//
//	    return messageSource;
//	}
}
