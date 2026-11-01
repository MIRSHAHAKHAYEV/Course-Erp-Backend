package com.courseerpbackend.demo1.controller;

import com.courseerpbackend.demo1.dto.response.BaseResponse;
import com.courseerpbackend.demo1.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
public class TestController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/test")
    public BaseResponse<String> test(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BaseResponse.success(userDetails.getUsername());
    }
    @GetMapping("/test/no-auth")
    public BaseResponse<String> testNoAuth(){
        userService.getByEmail("asdjfsdfsdf");
        return BaseResponse.success("Course ERP - No Auth");
    }
}
