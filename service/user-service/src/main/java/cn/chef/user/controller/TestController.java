package cn.chef.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("hello")
    String hello(){
        return "hello dear";
    }
    @GetMapping("my")
    String xiba(){
        return"hello my dear";
    }

}
