package com.example.test11;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String test(){
        return "redirect:/article/list";
    }
}
