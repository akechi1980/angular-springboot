package com.exp.demo.apidemo.dao.memory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // ユーザ名
    private String name;
    // パスワード
    private String password;
    // 年齢
    private int age;
    // メールアドレス
    private String email;
}
