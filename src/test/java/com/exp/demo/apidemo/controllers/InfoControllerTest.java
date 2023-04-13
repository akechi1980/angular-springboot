package com.exp.demo.apidemo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.exp.demo.apidemo.model.InfoModel;
import com.exp.demo.apidemo.model.ResponseObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 结合测试RestAPI的测试类
 * 
 */
@Slf4j
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InfoControllerTest {

    @Autowired
    TestRestTemplate restTemplate;
    
    @Autowired
    ObjectMapper objectMapper;
    

    /*
     * 动作确认用RestAPI，直接返回 “Version 0.0.1” TEST
     */
    @Test
    public void testGetVersion() {

        ResponseObject rest = restTemplate.getForObject("/info/version", ResponseObject.class);
        log.info("rest: {}", rest);
        InfoModel info = objectMapper.convertValue(rest.getData(), InfoModel.class);
        
        assertNotNull(rest);
        assertEquals("Version 0.0.1", info.getVersion());
        assertNotNull(info.getDatetime());

    }


    /*
     * 获取当前服务器日期时间 TEST
     */
    @Test
    public void testGetDateTime() {

        ResponseObject rest = restTemplate.getForObject("/info/datetime", ResponseObject.class);
        log.info("rest: {}", rest);
        assertNotNull(rest);
        assertEquals(200, rest.getCode());

    }
    

}
