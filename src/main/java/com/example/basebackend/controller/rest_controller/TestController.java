package com.example.basebackend.controller.rest_controller;

import com.example.basebackend.dto.TestDto;
import com.example.basebackend.service.core.TestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/testController")
public class TestController extends BaseController<TestDto> {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        super(testService);
        this.testService = testService;
    }

}
