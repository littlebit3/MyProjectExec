package com.vt.controller;

import com.vt.domain.Comment;
import com.vt.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MyController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/test")
    public Object test(){
        List<Comment> list = commentService.list();
        long count = commentService.count();
        return count;
    }

}
