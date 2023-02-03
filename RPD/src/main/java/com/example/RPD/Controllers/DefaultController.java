package com.example.RPD.Controllers;

import com.example.RPD.Models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/")
    public String regView( Account user)
    {
        return "/Home";
    }

//     @GetMapping("/error")
//    public String errorView()
//    {
//        return "1";
//    }
}
