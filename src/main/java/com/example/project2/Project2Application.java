package com.example.project2;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableCaching //CACHE-REDIS
@EnableAsync
public class Project2Application implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);
	}
    
	@Bean //org.springframework.web.servlet.LocaleResolver
	public LocaleResolver localeResolver() {
		//org.springframework.web.servlet.i18n.SessionLocaleResolver
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("en"));
		return slr;
	}
	@Bean //hello?lang=vi
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");// cau hinh params tham so gui len 
		return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	//on the second, minute, hour, day of month, month, and day of week. 
	@Scheduled(cron = "0 * * * * *")
	public void hello() {
		System.out.println("Hello");
	}
}
