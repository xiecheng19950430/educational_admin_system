package com.ebay.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.ebay.core.ShopTokenResolver;
import com.ebay.core.TokenResolver;

import java.util.List;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/6
 */

@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private SignInterceptor signInterceptor;
    @Autowired
    private TokenResolver tokenResolver;

    @Autowired
    private ShopTokenResolver shopTokenResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenResolver);
        argumentResolvers.add(shopTokenResolver);
    }
}
