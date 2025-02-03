package com.example.springcoredemo.config;

import java.util.Locale;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.example.springcoredemo.filter.LangFilter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/messages"); // Tên tệp tài nguyên của bạn
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public FilterRegistrationBean<LangFilter> langFilter() {
        FilterRegistrationBean<LangFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LangFilter());
        registrationBean.addUrlPatterns("/*"); // áp dụng filter cho các URL cần quốc tế hóa
        return registrationBean;
    }

}
