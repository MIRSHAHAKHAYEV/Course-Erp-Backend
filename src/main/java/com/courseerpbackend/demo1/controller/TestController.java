package com.courseerpbackend.demo1.controller;

import com.courseerpbackend.demo1.dto.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public BaseResponse<String> test(){
        return BaseResponse.success("Course ERP");
    }
}
