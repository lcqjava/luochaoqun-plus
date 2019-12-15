package com.luochaoqun.plus.ideas.xiaoyuandiancan.user.controller;


import com.luochaoqun.plus.ideas.xiaoyuandiancan.user.entity.User;
import com.luochaoqun.plus.ideas.xiaoyuandiancan.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luochaoqun
 * @since 2019-12-15
 */
@Slf4j
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/list")
    public List<User> list() {
        log.info("list#############");
        return userService.list();
    }

}
