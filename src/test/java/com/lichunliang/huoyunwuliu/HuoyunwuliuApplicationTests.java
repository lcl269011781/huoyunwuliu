package com.lichunliang.huoyunwuliu;

import com.lichunliang.huoyunwuliu.mapper.SourceInformationMapper;
import com.lichunliang.huoyunwuliu.pojo.SourceInformation;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HuoyunwuliuApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private SourceInformationMapper sourceInformationMapper;
    @Test
    public void contextLoads() {

//        List<SourceInformation> sourceInformations = sourceInformationMapper.selectSourceInformationList();
//        for (SourceInformation sourceInformation : sourceInformations) {
//            System.out.println(sourceInformation);
//        }

    }

}

