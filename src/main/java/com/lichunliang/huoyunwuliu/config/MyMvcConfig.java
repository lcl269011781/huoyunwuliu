package com.lichunliang.huoyunwuliu.config;

import com.lichunliang.huoyunwuliu.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ━━━━━━━━━神兽出没━━━━━━━━━
 * <p>
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * ━━━━━━━━━感觉萌萌哒━━━━━━━━━
 */
@Configuration
public class MyMvcConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            //视图映射
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("/login.html");
                registry.addViewController("/index.html").setViewName("/index.html");
                registry.addViewController("/register.html").setViewName("/register.html");
                registry.addViewController("/alliance.html").setViewName("/alliance.html");
                registry.addViewController("/agreement.html").setViewName("/agreement.html");
                registry.addViewController("/freight-details.html").setViewName("/freight-details.html");
                registry.addViewController("/pay.html").setViewName("/pay.html");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                String[] patterns = new String[]{"/css/*", "/images/**", "/js/**", "/", "/login.html", "/register.html",
                        "/user/**", "/agreement.html", "/index.html","/alliance.html","/callback.html"};
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(patterns);
            }
        };
        return webMvcConfigurer;
    }

}
