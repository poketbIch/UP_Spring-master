package com.example.RPD.Controllers;

import com.example.RPD.Models.Account;
import com.example.RPD.Models.Dolj;
import com.example.RPD.Models.Employee;
import com.example.RPD.Models.Role;
import com.example.RPD.Repository.AccountRepository;
import com.example.RPD.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;
@Controller
@RequestMapping("/Authorization")
public class RegistrationController {
    @Autowired
    AccountRepository accountRepositry;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/Registration")
    public String regView(Account user, Employee employee, Model model)
    {
        Iterable<Employee> listEmployee= employeeRepository.findAll();
        Iterable<Account> listAccount=accountRepositry.findAll();
        model.addAttribute("listEmployee",listEmployee);
        model.addAttribute("listAccount",listAccount);
        return "Authorization/Registration";
    }
    @PostMapping("/Registration")
    public String regUser(
            @Valid Account user,
            @Valid Employee employee,
            BindingResult bindingResult,
            Model model

    )

    {
        if (bindingResult.hasErrors())
        {
            return "/Athorization/Registration";
        }
        if( accountRepositry.findByUsername(user.getUsername())!=null)
        {
            model.addAttribute("error","Такой пользователь уже существует");
            return "/Athorization/Registration";
        }
        try {
        user.setActive(true);
        user.setRole(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        employee.setAccount(user);

            employeeRepository.save(employee);
            accountRepositry.save(user);
        }
        catch (Exception e)
        {
            return "redirect:/Home";
        }
        return "redirect:/Home";
    }
}
