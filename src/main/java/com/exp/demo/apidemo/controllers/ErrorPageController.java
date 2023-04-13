package com.exp.demo.apidemo.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {
    
    @RequestMapping("/error")
    public String handleError() {
    	return "index.html"; // 该资源位于resources/static目录下
    }


    
}
