package com.example.RPD.Controllers;

import com.example.RPD.Models.*;
import com.example.RPD.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','HR')")
public class HRController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AdressRepository adressRepository;

    @Autowired
    DoljRepository doljRepository;

    //Employee
    @GetMapping("/HR/EmployeeView")
    public String Employee(Model model)
    {
        Iterable<Employee> listEmp= employeeRepository.findAll();
        model.addAttribute("emps",listEmp);
        return "/HR/EmployeeView";
    }

    @GetMapping("/HR/EmployeeAdd")
    public String EmpAddView(Model model, Employee employee, Account account, Dolj dolj, Department department)
    {
        Iterable<Dolj> listDolj= doljRepository.findAll();
        Iterable<Account> listAccount=accountRepository.findAll();
        Iterable<Department> listDepartment=departmentRepository.findAll();
        model.addAttribute("listDolj",listDolj);
        model.addAttribute("listAccount",listAccount);
        model.addAttribute("listDepartment",listDepartment);
        return "/HR/EmployeeAdd";
    }
    @PostMapping("/HR/EmployeeAdd")
    public String EmpAdd(
            @Valid Employee employee,
            @Valid Account account,
            @RequestParam Long listDolj,
            @RequestParam Long listDepartment,
            BindingResult bindingResult,
            Model model)
    {

        if(bindingResult.hasErrors())
        {
            return "/HR/EmployeeAdd";
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        employee.setAccount(account);
        employee.setDolj(doljRepository.findById(listDolj).orElseThrow());
        employee.setDepartment(departmentRepository.findById(listDepartment).orElseThrow());
        employeeRepository.save(employee);


        return "redirect:/HR/EmployeeView";
    }
    @GetMapping("/HR/EmployeeFilter")
    public String EmpFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Employee> emp=employeeRepository.findByNameContaining(name);
        model.addAttribute("empfiltered",emp);
        return "/HR/EmployeeFilter";
    }
    @GetMapping("/HR/EmployeeFilterNoCategory")
    public String EmpFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Employee> emp=employeeRepository.findByName(name);
        model.addAttribute("empfiltered",emp);
        return "/HR/EmployeeFilterNoCategory";
    }

    @GetMapping("/HR/EmployeeDetail/{id}")
    public String EmpDetails(@PathVariable Long id,
                             Model model)
    {
        Optional<Employee> employee = employeeRepository.findById(id);
        Optional<Department> department = departmentRepository.findById(id);
        Optional<Account> account = accountRepository.findById(id);
        Optional<Dolj> dolj = doljRepository.findById(id);
        ArrayList<Employee> res = new ArrayList<Employee>();
        ArrayList<Account> res_acc = new ArrayList<Account>();
        ArrayList<Dolj> res_dolj = new ArrayList<Dolj>();
        ArrayList<Department> res_dep = new ArrayList<Department>();
        employee.ifPresent(res::add);
        account.ifPresent(res_acc::add);
        dolj.ifPresent(res_dolj::add);
        department.ifPresent(res_dep::add);

        model.addAttribute("employee",res);
        model.addAttribute("account",res_acc);
        model.addAttribute("dolj",res_dolj);
        model.addAttribute("department",res_dep);
        return "/HR/EmployeeDetail";
    }
    @GetMapping ("/HR/EmployeeDelete/{id}")
    public String EmpDelete(@PathVariable Long id)
    {

//        accountRepository.deleteById(id);
        employeeRepository.deleteById(id);
        return "redirect:/HR/EmployeeView";
    }
    @GetMapping ("/HR/EmployeeEdit/{id}")
    public String EmpEditView(@PathVariable Long id,Employee employees,Account account,Department department,
                              Model model)
    {
        employees= employeeRepository.findById(id).orElseThrow();
      Iterable<Dolj>  dolj=doljRepository.findAll();
        Iterable<Department>  departments=departmentRepository.findAll();
        model.addAttribute("employees",employees);
        model.addAttribute("dolj",dolj);
        model.addAttribute("department",departments);
        return "/HR/EmployeeEdit";
    }
    @PostMapping ("/HR/EmployeeEdit/{id}")
    public String EmpEdit(   @Valid Employee employee,
                             BindingResult bindingResult,
                             @RequestParam Long dolj,
                             @RequestParam Long department,
                             @RequestParam Long account,

                             Model model)
    {

        if (bindingResult.hasErrors())
        {
            model.addAttribute("employee",employee);

            return "/HR/EmployeeEdit";
        }
        employee.setAccount(accountRepository.findById(account).orElseThrow());
        employee.setDepartment(departmentRepository.findById(department).orElseThrow());
        employee.setDolj(doljRepository.findById(dolj).orElseThrow());


        employeeRepository.save(employee);
        return "redirect:/HR/EmployeeView";
    }

    //Departments
    @GetMapping("/HR/DepartmentView")
    public String Department(Model model)
    {
        Iterable<Department> listDepartment= departmentRepository.findAll();
        model.addAttribute("listDepartment",listDepartment);
        return "/HR/DepartmentView";
    }
    @GetMapping("/HR/DepartmentDetail/{id}")
    public String DepartmentDetails(@PathVariable Long id,
                             Model model)
    {
        Optional<Department> department = departmentRepository.findById(id);

        ArrayList<Department> res = new ArrayList<Department>();

        department.ifPresent(res::add);

        model.addAttribute("department",res);

        return "/HR/DepartmentDetail";
    }
    @GetMapping("/HR/DepartmentAdd")
    public String DepartmentAddView(Model model, Department department)
    {
        return "/HR/DepartmentAdd";
    }
    @PostMapping("/HR/DepartmentAdd")
    public String DepartmentAdd(

            @Valid Department department,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/HR/DepartmentAdd";
        }

        departmentRepository.save(department);


        return "redirect:/HR/DepartmentView";
    }
    @GetMapping ("/HR/DepartmentDelete/{id}")
    public String DepartmentDelete(@PathVariable Long id)
    {
        departmentRepository.deleteById(id);
        return "redirect:/HR/DepartmentView";
    }
    @GetMapping ("/HR/DepartmentEdit/{id}")
    public String DepartmentEditView(@PathVariable Long id,Department department,
                              Model model)
    {
        department= departmentRepository.findById(id).orElseThrow();
        model.addAttribute("department",department);
        return "/HR/DepartmentEdit";
    }
    @PostMapping ("/HR/DepartmentEdit/{id}")
    public String DepartmentEdit(   @Valid Department department,
                                    BindingResult bindingResult,
                             Model model)
    {
        model.addAttribute("department",department);

        if (bindingResult.hasErrors())
        {
            return "/HR/DepartmentEdit";
        }
        departmentRepository.save(department);


        return "redirect:/HR/DepartmentView";
    }
    @GetMapping("/HR/DepartmentFilter")
    public String DepartmentFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Department> listDepartmentFilter=departmentRepository.findByLocationContaining(name);
        model.addAttribute("listDepartmentFilter",listDepartmentFilter);
        return "/HR/DepartmentFilter";
    }
    @GetMapping("/HR/DepartmentFilterNoCategory")
    public String DepartmentFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Department> listDepartmentFilter=departmentRepository.findByLocation(name);
        model.addAttribute("listDepartmentFilter",listDepartmentFilter);
        return "/HR/DepartmentFilterNoCategory";
    }


    ///Dolj
    @GetMapping("/HR/DoljView")
    public String Dolj(Model model)
    {
        Iterable<Dolj> listDolj= doljRepository.findAll();
        model.addAttribute("listDolj",listDolj);
        return "/HR/DoljView";
    }
    @GetMapping("/HR/DoljDetail/{id}")
    public String DoljDetails(@PathVariable Long id,
                                    Model model)
    {
        Optional<Dolj> dolj = doljRepository.findById(id);
        ArrayList<Dolj> res = new ArrayList<Dolj>();
        dolj.ifPresent(res::add);
        model.addAttribute("doljs",res);

        return "/HR/DoljDetail";
    }
    @GetMapping("/HR/DoljAdd")
    public String DoljAddView(Model model, Dolj dolj)
    {
        return "/HR/DoljAdd";
    }
    @PostMapping("/HR/DoljAdd")
    public String DoljAdd(
            @Valid Dolj dolj,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "/HR/DoljAdd";
        }

        doljRepository.save(dolj);

        return "redirect:/HR/DoljView";
    }
    @GetMapping ("/HR/DoljDelete/{id}")
    public String DoljDelete(@PathVariable Long id)
    {
        doljRepository.deleteById(id);
        return "redirect:/HR/DoljView";
    }
    @GetMapping ("/HR/DoljEdit/{id}")
    public String DoljEditView(@PathVariable Long id,Dolj dolj,
                                     Model model)
    {
        dolj= doljRepository.findById(id).orElseThrow();
        model.addAttribute("dolj",dolj);
        return "/HR/DoljEdit";
    }
    @PostMapping ("/HR/DoljEdit/{id}")
    public String DoljEdit(   @Valid Dolj dolj,
                                    BindingResult bindingResult,
                                    Model model)
    {
        model.addAttribute("dolj",dolj);

        if (bindingResult.hasErrors())
        {
            return "/HR/DoljEdit";
        }
        doljRepository.save(dolj);


        return "redirect:/HR/DoljView";
    }
    @GetMapping("/HR/DoljFilter")
    public String DoljFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Dolj> listDoljFilter=doljRepository.findByNameContaining(name);
        model.addAttribute("listDoljFilter",listDoljFilter);
        return "/HR/DoljFilter";
    }
    @GetMapping("/HR/DoljFilterNoCategory")
    public String DoljFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Dolj> listDoljFilter=doljRepository.findByName(name);
        model.addAttribute("listDoljFilter",listDoljFilter);
        return "/HR/DoljFilterNoCategory";
    }
    //Adress

    @GetMapping("/HR/AdressView")
    public String Adress(Model model)
    {
        Iterable<Adress> listAdress= adressRepository.findAll();
        model.addAttribute("listAdress",listAdress);
        return "/HR/AdressView";
    }

    @GetMapping("/HR/AdressAdd")
    public String AdressAddView(Model model, Employee employee,Adress adress)
    {

        return "/HR/AdressAdd";
    }
    @PostMapping("/HR/AdressAdd")
    public String AdressAdd(
            @Valid Adress adress,
            BindingResult bindingResult,
            Model model)
    {

        if(bindingResult.hasErrors())
        {
            return "/HR/AdressAdd";
        }
        adressRepository.save(adress);

        return "redirect:/HR/AdressView";
    }

    @GetMapping("/HR/AdressFilter")
    public String AdressFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Adress> listAdressFilter=adressRepository.findByLocationContaining(name);
        model.addAttribute("listAdressFilter",listAdressFilter);
        return "/HR/AdressFilter";
    }
    @GetMapping("/HR/AdressFilterNoCategory")
    public String AdressFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Adress> listAdressFilter=adressRepository.findFirstByLocation(name);
        model.addAttribute("listAdressFilter",listAdressFilter);
        return "/HR/AdressFilterNoCategory";
    }
    @GetMapping("/HR/AdressDetail/{id}")
    public String AdressDetails(@PathVariable Long id,
                             Model model)
    {
        ArrayList<Adress> res = new ArrayList<Adress>();
        adressRepository.findById(id).ifPresent(res::add);
        model.addAttribute("adress",res);

        return "/HR/AdressDetail";
    }
    @GetMapping ("/HR/AdressDelete/{id}")
    public String AdressDelete(@PathVariable Long id)
    {

//        accountRepository.deleteById(id);
        employeeRepository.deleteById(id);
        return "redirect:/HR/AdressView";
    }
    @GetMapping ("/HR/AdressEdit/{id}")
    public String AdressEditView(@PathVariable Long id,Adress adress,Employee employee,
                              Model model)
    {
        adress= adressRepository.findById(id).orElseThrow();
        model.addAttribute("adress",adress);
        return "/HR/AdressEdit";
    }
    @PostMapping ("/HR/AdressEdit/{id}")
    public String AdressEdit(   @Valid Adress adress,


                             BindingResult bindingResult,
                             Model model)
    {

        if (bindingResult.hasErrors())
        {

            return "/HR/AdressEdit";
        }
        // account = accountRepository.save(account);
        adressRepository.save(adress);
        return "redirect:/HR/AdressView";
    }
    @GetMapping("/HR/AdressEmployee")
    public String AdressEmpView(Model model, Adress adress)
    {

        Iterable<Adress> listAdress= adressRepository.findAll();
        Iterable<Employee> listEmp= employeeRepository.findAll();
        model.addAttribute("listAdress",listAdress);
        model.addAttribute("listEmp",listEmp);

        return "/HR/AdressEmployee";
    }
    @PostMapping("/HR/AdressEmployee")
    public String AdressEmp(Model model,
                            @RequestParam String listEmp, @RequestParam String listAdress)
    {
        Employee employee = employeeRepository.findFirstByName(listEmp);
        Adress adress = adressRepository.findByLocation(listAdress);

        adress.getEmployees().add(employee);
        adressRepository.save(adress);
        return "redirect:/HR/AdressView";
    }


}
