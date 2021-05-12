package com.example.redis.bean;

import lombok.Data;

/**
 * @author xiaohai
 * @date 2021/5/12 10:17
 * @description
 */
@Data
public class Student {

    private Integer id;

    private String username;

    private String password;

    public Student(){}

    public Student(Integer id,String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
