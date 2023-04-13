package com.exp.demo.apidemo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.exp.demo.apidemo.dao.UserRepository;
import com.exp.demo.apidemo.dao.memory.User;
import com.exp.demo.apidemo.model.ResponseObject;
import com.exp.demo.apidemo.model.ResponseQuery;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 结合测试UserController的测试类
 * 
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // 初始化测试数据
        User person = new User();
        person.setId(1L);
        person.setName("Test Person");
        userRepository.save(person);
        log.info("==== setUp ====================");
    }

    @AfterEach
    public void tearDown() {
        // 清除测试数据
        userRepository.deleteAll();
        log.info("==== tearDown =================");
    }

    
    @Test
    public void testGetAllPersons() {
        // ResponseEntity<ResponseQuery> response = restTemplate.exchange("/users",
        // HttpMethod.GET, null, new ParameterizedTypeReference<ResponseQuery>() {});

        ResponseEntity<ResponseQuery> response = restTemplate.getForEntity("/api/users", ResponseQuery.class);

        log.info("response: {}", response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseQuery tResponseQuery = response.getBody();

        assertNotNull(tResponseQuery);
        List<User> lst = objectMapper.convertValue(tResponseQuery.getLst(), new TypeReference<List<User>>() {
        });

        assertEquals(0, tResponseQuery.getCode());

        assertEquals(1, tResponseQuery.getPage());
        assertEquals(10, tResponseQuery.getSize());

        User person = (User) lst.get(0);
        assertEquals(1, lst.size());
        assertEquals("Test Person", person.getName());

    }

    @Test
    public void testGetPersonById() {
        long id = userRepository.findAll().get(0).getId();
        ResponseEntity<ResponseObject> response = restTemplate.getForEntity("/api/users/" + id, ResponseObject.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseObject tResponseObject = response.getBody();
        assertNotNull(tResponseObject);
        User tUser = objectMapper.convertValue(tResponseObject.getData(), new TypeReference<User>() {
        });

        assertEquals(1, tUser.getId());
        assertEquals("Test Person", tUser.getName());

    }

    /**
     * 测试新增用户
     */
    @Test
    public void testAddPerson() {
        User person = new User();
        person.setName("Test Person 2");

        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("/api/users", person, ResponseObject.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseObject tResponseObject = response.getBody();
        assertNotNull(tResponseObject);
        User tUser = objectMapper.convertValue(tResponseObject.getData(), new TypeReference<User>() {
        });
        
        // 新增时，id为null 不知道ID会是多少所以不判断
        // assertEquals(person.getId(), tUser.getId());
        assertEquals("Test Person 2", tUser.getName());

    }

    /**
     * 测试更新用户
     */
    @Test
    public void testUpdatePerson() {
        long id = userRepository.findAll().get(0).getId();
        User person = new User();
        person.setId(id);
        person.setName("Test Person 3");

        restTemplate.put("/api/users/" + id, person);

        ResponseEntity<ResponseObject> response = restTemplate.getForEntity("/api/users/" + id, ResponseObject.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseObject tResponseObject = response.getBody();
        assertNotNull(tResponseObject);
        User tUser = objectMapper.convertValue(tResponseObject.getData(), new TypeReference<User>() {
        });

        assertEquals(id, tUser.getId());
        assertEquals("Test Person 3", tUser.getName());

    }

    /**
     * 测试删除用户
     */
    @Test
    public void testDeletePerson() {
        long id = userRepository.findAll().get(0).getId();
        restTemplate.delete("/api/users/" + id);

        ResponseEntity<ResponseObject> response = restTemplate.getForEntity("/api/users/" + id, ResponseObject.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseObject tResponseObject = response.getBody();
        assertNotNull(tResponseObject);
        assertEquals(1, tResponseObject.getCode());
        assertEquals("NG", tResponseObject.getMessage());

    }

}
