package com.exp.demo.apidemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exp.demo.apidemo.dao.UserRepository;
import com.exp.demo.apidemo.dao.memory.User;
import com.exp.demo.apidemo.model.ResponseQuery;
import com.exp.demo.apidemo.model.ResponseObject;

/**
 * UserController
 * 用于提供用户信息的RestAPI
 * 
 * @author akechi
 * @version 0.0.1
 * @since 2023-04-13
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    /**
     * 获取所有用户信息
     * 
     * @return ResponseQuery
     * 最终会被封装到 ResponseEntity
     */
    @GetMapping
    public ResponseQuery getAllUsers() {
        List<User> lst = userRepository.findAll();
        return new ResponseQuery(0, 1, 10, lst.size(), lst);
    }

    @GetMapping("/{id}")
    public ResponseObject getUserById(@PathVariable Long id) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            return new ResponseObject(0, "OK", existingUser);
        }

        return new ResponseObject(1, "NG", null);
    }

    @PostMapping
    public ResponseObject createUser(@RequestBody User user) {

        User existingUser = userRepository.save(user);

        if (existingUser != null) {
            return new ResponseObject(200, "OK", existingUser);
        }

        return new ResponseObject(200, "NG", null);

    }

    @PutMapping("/{id}")
    public ResponseObject updateUser(@PathVariable Long id, @RequestBody User user) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setName(user.getName());
            userRepository.save(existingUser);

            return new ResponseObject(200, "OK", existingUser);
        }
        return new ResponseObject(200, "NG", null);
    }

    @DeleteMapping("/{id}")
    public ResponseObject deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);

        return new ResponseObject(200, "OK", null);
    }

}
