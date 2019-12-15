package com.luochaoqun.plus.ideas.xiaoyuandiancan;

import com.luochaoqun.plus.ideas.xiaoyuandiancan.user.entity.User;
import com.luochaoqun.plus.ideas.xiaoyuandiancan.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusDemoApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void select(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}