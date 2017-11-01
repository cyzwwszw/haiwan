package com.lincomb.haiwan.config;

import com.lincomb.haiwan.Interceptor.LogonInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yongsheng.he
 * @describe
 * @date 2017/10/31 18:37
 */
@Configuration
@EnableWebMvc
@ComponentScan
@Slf4j
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public WebConfig() {
        super();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截规则：拦截/backend/下面的所有URL，除了login，其他都拦截判断
        registry.addInterceptor(new LogonInterceptor()).addPathPatterns("/backend/**").excludePathPatterns("/backend/adminUser/toLogin", "/backend/adminUser/doLogin");
        super.addInterceptors(registry);
    }

}