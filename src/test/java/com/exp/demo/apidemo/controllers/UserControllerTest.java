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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.exp.demo.apidemo.dao.UserRepository;
import com.exp.demo.apidemo.dao.memory.User;
import com.exp.demo.apidemo.model.ResponseQuery;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 结合测试UserController的测试类
 * 
 */
@Slf4j
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        // ResponseEntity<ResponseQuery> response = restTemplate.exchange("/users", HttpMethod.GET, null, new ParameterizedTypeReference<ResponseQuery>() {});

        ResponseEntity<ResponseQuery> response = restTemplate.getForEntity("/users", ResponseQuery.class);

        log.info("response: {}", response);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseQuery tResponseQuery = response.getBody();
        List<User> lst = objectMapper.convertValue(tResponseQuery.getLst(), new TypeReference<List<User>>(){});

        assertEquals(0, tResponseQuery.getCode());
        assertEquals(1, tResponseQuery.getPage());
        assertEquals(10, tResponseQuery.getSize());
  
        User person = (User) lst.get(0);
        assertEquals(1, lst.size());
        assertEquals("Test Person", person.getName());

        
    }

    // @Test
    // public void testGetPersonById() {
    //     long id = personRepository.findAll().get(0).getId();
    //     ResponseEntity<Person> response = restTemplate.getForEntity("/persons/" + id, Person.class);
    //     assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    //     assertThat(response.getBody().getName(), equalTo("Test Person"));
    // }

    // @Test
    // public void testCreatePerson() {
    //     Person person = new Person();
    //     person.setName("New Person");
    //     person.setAge(25);
    //     HttpEntity<Person> request = new HttpEntity<>(person);
    //     ResponseEntity<Person> response = restTemplate.postForEntity("/persons", request, Person.class);
    //     assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    //     assertThat(response.getBody().getId(), notNullValue());
    // }

    // @Test
    // public void testUpdatePerson() {
    //     long id = personRepository.findAll().get(0).getId();
    //     Person person = new Person();
    //     person.setId(id);
    //     person.setName("Updated Person");
    //     person.setAge(35);
    //     HttpEntity<Person> request = new HttpEntity<>(person);
    //     ResponseEntity<Person> response = restTemplate.exchange("/persons/" + id, HttpMethod.PUT, request, Person.class);
    //     assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    //     assertThat(response.getBody().getName(), equalTo("Updated Person"));
    // }

    // @Test
    // public void testDeletePerson() {
    //     long id = personRepository.findAll().get(0).getId();
    //     restTemplate.delete("/persons/" + id);
    //     assertThat(personRepository.count(), equalTo(0L));
    // }
    
}
