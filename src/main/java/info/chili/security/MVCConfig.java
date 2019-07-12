/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security;

import info.chili.security.filter.XssFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author k26758
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {
 
    @Autowired
    private XssFilter xssInterceptor;
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       
    }
}