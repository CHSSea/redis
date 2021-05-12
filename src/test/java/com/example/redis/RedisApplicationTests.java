package com.example.redis;

import com.example.redis.bean.Student;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void setObject(){
        Student student = new Student(1,"helong","123456");
        redisTemplate.opsForValue().set("student",student);
        System.out.println("success");
    }

    @Test
    public void getObject(){
        Student student = (Student)redisTemplate.opsForValue().get("student");
        System.out.println(student.toString());
    }

    @Test
    public void setListObject(){
        List<Student> list = Lists.newArrayList();
        list.add(new Student(1,"Jon","123456"));
        list.add(new Student(2,"Tom","123456"));
        redisTemplate.opsForValue().set("list",list);
        System.out.println("success");
    }

    @Test
    public void getListObject(){
        List<Student> list = (List)redisTemplate.opsForValue().get("list");
        list.forEach(s ->{
            System.out.println(s.toString());
        });
    }

    @Test
    public void hasKey(){
        Boolean result = redisTemplate.hasKey("list");
        System.out.println(result);
    }

    @Test
    public void del(){
        Boolean result = redisTemplate.delete("name");
        System.out.println(result);
    }

    @Test
    public void setList(){
        //插入到表尾
        redisTemplate.opsForList().rightPush("student:list","tom is man");
        redisTemplate.opsForList().rightPush("student:list","marry is woman");
        //插入到表头
        redisTemplate.opsForList().leftPush("student:list","helong not kown");
        System.out.println("success");
    }

    @Test
    public void getList(){
        List<Object> range = redisTemplate.opsForList().range("student:list",0,-1);
        range.forEach(a -> {
            System.out.println(a.toString());
        });
    }

    @Test
    public void getRightList(){
        Object object = redisTemplate.opsForList().rightPop("student:list");
        System.out.println(object.toString());
    }

    @Test
    public void getLeftList(){
        Object object = redisTemplate.opsForList().leftPop("student:list");
        System.out.println(object.toString());
    }

    @Test
    public void setHash(){
        Map<String,Object> map = new HashMap<>();
        map.put("tom","123456");
        map.put("jon","111222");
        map.put("marry","456789");
        redisTemplate.opsForHash().putAll("hash",map);
        System.out.println("success");
    }

    @Test
    public void getHash(){
        Map<Object, Object> hash = redisTemplate.opsForHash().entries("hash");
        for (Map.Entry<Object, Object> entry : hash.entrySet()){
            System.out.println(entry.getKey() + "----" + entry.getValue());
        }
    }

    @Test
    public void setHashExpire(){
        redisTemplate.expire("hash",20, TimeUnit.SECONDS);
        System.out.println("success");
    }

    @Test
    public void setHashTail(){
        redisTemplate.opsForHash().putIfAbsent("hash","lee","345678");
        System.out.println("success");
    }

    @Test
    public void setSet(){
        redisTemplate.opsForSet().add("set","day","month","month",null,"year");
        System.out.println("success");
    }

    @Test
    public void getSet(){
        Set<Object> set = redisTemplate.opsForSet().members("set");
        set.forEach(a -> {
            System.out.println(a.toString());
        });
    }
}
