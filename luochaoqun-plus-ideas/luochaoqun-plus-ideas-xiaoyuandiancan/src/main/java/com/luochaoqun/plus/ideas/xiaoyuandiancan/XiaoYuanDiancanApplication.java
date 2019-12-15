package com.luochaoqun.plus.ideas.xiaoyuandiancan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.luochaoqun.plus.ideas.xiaoyuandiancan.*.mapper")
@SpringBootApplication
public class XiaoYuanDiancanApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoYuanDiancanApplication.class, args);
    }

}
