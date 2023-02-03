package com.example.RPD.Controllers;

import com.example.RPD.Models.*;
import com.example.RPD.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Policeman")
@PreAuthorize("hasAnyAuthority('OFFICER')")
public class PolicemanController {
    @Autowired
    CaseParticipatorRepository caseParticipatorRepository;
    @Autowired
    CaseParticipantCategoryRepository caseParticipantCategoryRepository;
    @Autowired
    CrimeCaseRepository crimeCaseRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/CrimeCase/CrimeCaseView")
    public String CrimeCase(Model model)
    {
        Iterable<CrimeCase> listCase= crimeCaseRepository.findAll();
        model.addAttribute("listCase",listCase);
        return "/Policeman/CrimeCase/CrimeCaseView";
    }
    @GetMapping("/CrimeCase/CrimeCaseAdd")
    public String CrimeCaseAddView(Model model, Employee employee, CrimeCase crimeCase)
    {

        Iterable<Employee> listEmp=employeeRepository.findAll();


        model.addAttribute("listEmp",listEmp);

        return "/Policeman/CrimeCase/CrimeCaseAdd";
    }
    @PostMapping("/CrimeCase/CrimeCaseAdd")
    public String CrimeCaseAdd(
            @Valid CrimeCase crimeCase,
            @RequestParam Long listEmp,
            BindingResult bindingResult,
            Model model)
    {

        if(bindingResult.hasErrors())
        {
            return "/Policeman/CrimeCase/CrimeCaseAdd";
        }
        crimeCase.setEmployee(employeeRepository.findById(listEmp).orElseThrow());
        crimeCaseRepository.save(crimeCase);


        return "redirect:/Policeman/CrimeCase/CrimeCaseView";
    }
    @GetMapping("/CrimeCase/CrimeCaseDetail/{id}")
    public String CrimeCaseDetails(@PathVariable Long id,
                             Model model)
    {

        CrimeCase crimeCase = crimeCaseRepository.findById(id).orElseThrow();

        model.addAttribute("crimeCase",crimeCase);
        return "/Policeman/CrimeCase/CrimeCaseDetail";
    }
    @GetMapping("/CrimeCase/CrimeCaseFilter")
    public String CrimeCaseFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<CrimeCase> crimeCases=crimeCaseRepository.findByDescriptionContaining(name);
        model.addAttribute("listCaseFiltered",crimeCases);
        return "/Policeman/CrimeCase/CrimeCaseFilter";
    }
    @GetMapping("/CrimeCase/CrimeCaseFilterNoCategory")
    public String CrimeCaseFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<CrimeCase> crimeCases=crimeCaseRepository.findByDescription(name);
        model.addAttribute("listCaseFiltered",crimeCases);
        return "/Policeman/CrimeCase/CrimeCaseFilterNoCategory";
    }
    @GetMapping ("/CrimeCase/CrimeCaseDelete/{id}")
    public String CrimeCaseDelete(@PathVariable Long id)
    {

//        accountRepository.deleteById(id);
        crimeCaseRepository.deleteById(id);
        return "redirect:/Policeman/CrimeCase/CrimeCaseView";
    }
    @GetMapping ("/CrimeCase/CrimeCaseEdit/{id}")
    public String CrimeCaseEditView(@PathVariable Long id,Employee employee,CrimeCase crimeCase,
                              Model model)
    {
        crimeCase= crimeCaseRepository.findById(id).orElseThrow();
        model.addAttribute("crimeCase",crimeCase);
        Iterable<Employee>  employees=employeeRepository.findAll();
        model.addAttribute("employees",employees);
        return "/Policeman/CrimeCase/CrimeCaseEdit";
    }
    @PostMapping ("/CrimeCase/CrimeCaseEdit/{id}")
    public String CrimeCaseEdit(
                             @Valid CrimeCase crimeCase,
                             @RequestParam Long employee,
                             BindingResult bindingResult,
                             Model model)
    {

        if (bindingResult.hasErrors())
        {
            model.addAttribute("employee",employee);
            return "/Policeman/CrimeCase/CrimeCaseEdit";
        }
        // account = accountRepository.save(account);
        crimeCase.setEmployee(employeeRepository.findById(employee).orElseThrow());
        crimeCaseRepository.save(crimeCase);
        return "redirect:/Policeman/CrimeCase/CrimeCaseView";
    }
    @GetMapping("/CrimeCase/CrimeCaseCaseParticipator")
    public String CrimeCaseCaseParticipatorView(Model model, CrimeCase crimeCase)
    {

        Iterable<CrimeCase> listCase= crimeCaseRepository.findAll();
        Iterable<CaseParticipator> listParticipator= caseParticipatorRepository.findAll();
        model.addAttribute("listCase",listCase);
        model.addAttribute("listParticipator",listParticipator);

        return "/Policeman/CrimeCase/CrimeCaseCaseParticipator";
    }
    @PostMapping("/CrimeCase/CrimeCaseCaseParticipator")
    public String CrimeCaseAndCaseParticipator(Model model,
                            @RequestParam String listCase, @RequestParam String listParticipator)
    {
        CrimeCase crimeCase = crimeCaseRepository.findCrimeCaseByDescription(listCase);
        CaseParticipator caseParticipator = caseParticipatorRepository.findBySurname(listParticipator);

        crimeCase.getCaseParticipators().add(caseParticipator);
        crimeCaseRepository.save(crimeCase);
        return "redirect:/Policeman/CrimeCase/CrimeCaseView";
    }
    //CaseParticipatorCategory
    @GetMapping("/CaseParticipatorCategory/CaseParticipatorCategoryView")
    public String CaseParticipatorCategory(Model model)
    {
        Iterable<CaseParticipantCategory> listCategory= caseParticipantCategoryRepository.findAll();
        model.addAttribute("listCategory",listCategory);
        return "/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryView";
    }
    @GetMapping("/CaseParticipatorCategory/CaseParticipatorCategoryAdd")
    public String CaseParticipatorCategoryAddView(Model model, CaseParticipantCategory caseParticipantCategory)
    {

        return "/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryAdd";
    }
    @PostMapping("/CaseParticipatorCategory/CaseParticipatorCategoryAdd")
    public String CaseParticipatorCategoryAdd(
            @Valid CaseParticipantCategory caseParticipantCategory,

            BindingResult bindingResult,
            Model model)
    {

        if(bindingResult.hasErrors())
        {
            return "/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryAdd";
        }

        caseParticipantCategoryRepository.save(caseParticipantCategory);


        return "redirect:/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryView";
    }

    @GetMapping("/CaseParticipatorCategory/CaseParticipatorCategoryFilter")
    public String CaseParticipatorCategoryFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<CaseParticipantCategory> caseParticipantCategories=caseParticipantCategoryRepository.findByCategoryContaining(name);
        model.addAttribute("caseParticipantCategoriesFilter",caseParticipantCategories);
        return "/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryFilter";
    }
    @GetMapping("/CaseParticipatorCategory/CaseParticipatorCategoryFilterNoCategory")
    public String CaseParticipatorCategoryFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<CaseParticipantCategory> caseParticipantCategories=caseParticipantCategoryRepository.findByCategory(name);
        model.addAttribute("caseParticipantCategoriesFilter",caseParticipantCategories);
        return "/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryFilterNoCategory";
    }
    @GetMapping ("/CaseParticipatorCategory/CaseParticipatorCategoryDelete/{id}")
    public String CaseParticipatorCategoryDelete(@PathVariable Long id)
    {

        caseParticipantCategoryRepository.deleteById(id);
        return "redirect:/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryView";
    }
    @GetMapping ("/CaseParticipatorCategory/CaseParticipatorCategoryEdit/{id}")
    public String CaseParticipatorCategoryEditView(@PathVariable Long id,CaseParticipantCategory caseParticipantCategory,
                                    Model model)
    {
        caseParticipantCategory= caseParticipantCategoryRepository.findById(id).orElseThrow();
        model.addAttribute("caseParticipantCategory",caseParticipantCategory);

        return "/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryEdit";
    }
    @PostMapping ("/CaseParticipatorCategory/CaseParticipatorCategoryEdit/{id}")
    public String CaseParticipatorCategoryEdit(
            @Valid CaseParticipantCategory caseParticipantCategory,
            BindingResult bindingResult,
            Model model)
    {

        if (bindingResult.hasErrors())
        {
            return "/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryEdit";
        }
        // account = accountRepository.save(account);
        caseParticipantCategoryRepository.save(caseParticipantCategory);
        return "redirect:/Policeman/CaseParticipatorCategory/CaseParticipatorCategoryView";
    }
    //CaseParticipator
    @GetMapping("/CaseParticipator/CaseParticipatorView")
    public String CaseParticipator(Model model)
    {
        Iterable<CaseParticipator> listParticipator= caseParticipatorRepository.findAll();
        model.addAttribute("listParticipator",listParticipator);
        return "/Policeman/CaseParticipator/CaseParticipatorView";
    }
    @GetMapping("/CaseParticipator/CaseParticipatorAdd")
    public String CaseParticipatorAddView(Model model, CaseParticipator caseParticipator,CaseParticipantCategory caseParticipantCategory)
    {

        Iterable<CaseParticipantCategory> listCategory= caseParticipantCategoryRepository.findAll();


        model.addAttribute("listCategory",listCategory);

        return "/Policeman/CaseParticipator/CaseParticipatorAdd";
    }
    @PostMapping("/CaseParticipator/CaseParticipatorAdd")
    public String CaseParticipatorAdd(
            @Valid CaseParticipator caseParticipator,
            @RequestParam Long caseParticipantCategory,
            BindingResult bindingResult,
            Model model)
    {

        if(bindingResult.hasErrors())
        {
            return "/Policeman/CaseParticipator/CaseParticipatorAdd";
        }
        caseParticipator.setCaseParticipantCategory(caseParticipantCategoryRepository.findById(caseParticipantCategory).orElseThrow());
        caseParticipatorRepository.save(caseParticipator);


        return "redirect:/Policeman/CaseParticipator/CaseParticipatorView";
    }
    @GetMapping("/CaseParticipator/CaseParticipatorDetail/{id}")
    public String CaseParticipatorDetails(@PathVariable Long id,
                                   Model model)
    {
        Optional<CaseParticipator> caseParticipator = caseParticipatorRepository.findById(id);

        ArrayList<CaseParticipator> res = new ArrayList<CaseParticipator>();
        caseParticipator.ifPresent(res::add);

        model.addAttribute("caseParticipator",res);


        return "/Policeman/CaseParticipator/CaseParticipatorDetail";
    }
    @GetMapping("/CaseParticipator/CaseParticipatorFilter")
    public String CaseParticipatorFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<CaseParticipator> caseParticipatorsFilter=caseParticipatorRepository.findByNameContaining(name);
        model.addAttribute("caseParticipatorsFilter",caseParticipatorsFilter);
        return "/Policeman/CaseParticipator/CaseParticipatorFilter";
    }
    @GetMapping("/CaseParticipator/CaseParticipatorFilterNoCategory")
    public String CaseParticipatorFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<CaseParticipator> caseParticipatorsFilter=caseParticipatorRepository.findByName(name);
        model.addAttribute("caseParticipatorsFilter",caseParticipatorsFilter);
        return "/Policeman/CaseParticipator/CaseParticipatorFilterNoCategory";
    }
    @GetMapping ("/CaseParticipator/CaseParticipatorDelete/{id}")
    public String CaseParticipatorDelete(@PathVariable Long id)
    {

//        accountRepository.deleteById(id);
        caseParticipatorRepository.deleteById(id);
        return "redirect:/Policeman/CaseParticipator/CaseParticipatorView";
    }
    @GetMapping ("/CaseParticipator/CaseParticipatorEdit/{id}")
    public String CaseParticipatorEditView(@PathVariable Long id,CaseParticipator caseParticipator,CaseParticipantCategory caseParticipantCategory,
                                    Model model)
    {
        caseParticipator= caseParticipatorRepository.findById(id).orElseThrow();
        model.addAttribute("caseParticipator",caseParticipator);
        Iterable<CaseParticipantCategory>  caseParticipantCategories=caseParticipantCategoryRepository.findAll();
        model.addAttribute("caseParticipantCategories",caseParticipantCategories);
        return "/Policeman/CaseParticipator/CaseParticipatorEdit";
    }
    @PostMapping ("/CaseParticipator/CaseParticipatorEdit/{id}")
    public String CaseParticipatorEdit(
            @Valid CaseParticipator caseParticipator,
            @RequestParam Long caseParticipantCategories,
            BindingResult bindingResult,
            Model model)
    {

        if (bindingResult.hasErrors())
        {
            model.addAttribute("caseParticipator",caseParticipator);
            return "/Policeman/CaseParticipator/CaseParticipatorEdit";
        }
        // account = accountRepository.save(account);
        caseParticipator.setCaseParticipantCategory(caseParticipantCategoryRepository.findById(caseParticipantCategories).orElseThrow());
        caseParticipatorRepository.save(caseParticipator);
        return "redirect:/Policeman/CaseParticipator/CaseParticipatorView";
    }
}
