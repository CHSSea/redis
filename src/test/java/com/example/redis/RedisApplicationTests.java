package com.example.redis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void set() {
        redisTemplate.opsForValue().set("name","helong");
    }

    @Test
    public void get(){
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);

    }

}
