package com.example.RPD.Controllers;


import com.example.RPD.Models.Account;
import com.example.RPD.Models.Role;
import com.example.RPD.Repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/Admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AccountController {
    @Autowired
    private AccountRepository accountRepositry;

    @GetMapping("/AccountView")
    public String userView(Model model)
    {
        model.addAttribute("listUser",accountRepositry.findAll());
        return "Admin/AccountView";
    }
    @GetMapping("/AccountEdit/{id}")
    public String userEditView(@PathVariable(name="id") Long id, Model model)
    {
        model.addAttribute("listRole", Role.values());
        model.addAttribute("userOne",accountRepositry.findById(id).orElseThrow());
        return "Admin/AccountEdit";
    }
    @PostMapping("/AccountEdit/{id}")
    public String userEditView(@PathVariable(name="id") Long id,
                               @RequestParam String username,
                               @RequestParam(name="role[]") String[] userRoles,
                               Model model)
    {
        Account user =accountRepositry.findById(id).orElseThrow();
        user.setUsername(username);
        user.getRole().clear();
        for (String roleOne:
                userRoles)
        {
            user.getRole().add(Role.valueOf(roleOne));
        }
        accountRepositry.save(user);
        return "redirect:/Admin/AccountView";
    }
}
