package com.example.test11.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/create")
    public String create(UserCreateForm userCreateForm){
        return "user_form";
    }
    @PostMapping("/user/create")
    public String create(UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user_form";
        }
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect","2개의 비밀번호가 일치하지않음");
            return "user_form";
        }
        this.userService.create(userCreateForm.getEmail(), userCreateForm.getPassword1());
        return "redirect:/article/list";
    }
}
