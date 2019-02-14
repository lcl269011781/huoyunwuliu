package com.lichunliang.huoyunwuliu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@MapperScan(basePackages = "com.lichunliang.huoyunwuliu.mapper")
@SpringBootApplication
public class HuoyunwuliuApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuoyunwuliuApplication.class, args);
    }


}

