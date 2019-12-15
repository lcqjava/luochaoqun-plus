package com.luochaoqun.plus.ideas.xiaoyuandiancan.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController("/file")
public class FileServerController {

    @Value("file-server-url")
    private String fileServerUrl;

    public String upload() {

        return null;
    }

}
