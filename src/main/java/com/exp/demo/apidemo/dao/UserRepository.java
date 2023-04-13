package com.exp.demo.apidemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exp.demo.apidemo.dao.memory.User;

public interface UserRepository extends JpaRepository<User, Long> {}
    
