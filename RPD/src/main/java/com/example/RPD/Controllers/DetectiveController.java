package com.example.RPD.Controllers;

import com.example.RPD.Models.*;
import com.example.RPD.Repository.CaseParticipatorRepository;
import com.example.RPD.Repository.ClueRepository;
import com.example.RPD.Repository.CrimeCaseRepository;
import com.example.RPD.Repository.TestimonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/Detective")
@PreAuthorize("hasAnyAuthority('DETECTIVE')")
public class DetectiveController {
    @Autowired
    CaseParticipatorRepository caseParticipatorRepository;
    @Autowired
    CrimeCaseRepository crimeCaseRepository;
    @Autowired
    TestimonyRepository testimonyRepository;
    @Autowired
    ClueRepository clueRepository;

    //Testimony
    @GetMapping("/Testimony/TestimonyView")
    public String Testimony(Model model)
    {
        Iterable<Testimony> listTestimony= testimonyRepository.findAll();
        model.addAttribute("listTestimony",listTestimony);
        return "/Detective/Testimony/TestimonyView";
    }
    @GetMapping("/Testimony/TestimonyAdd")
    public String TestimonyAddView(Model model, Testimony testimony)
    {
        Iterable<CaseParticipator> listParticipator= caseParticipatorRepository.findAll();
        model.addAttribute("listParticipator",listParticipator);
        return "/Detective/Testimony/TestimonyAdd";
    }
    @PostMapping("/Testimony/TestimonyAdd")
    public String TestimonyAdd(
            @Valid Testimony testimony,
            @RequestParam Long listParticipator,
            BindingResult bindingResult,
            Model model)
    {

        if(bindingResult.hasErrors())
        {
            return "/Detective/Testimony/TestimonyAdd";
        }

        testimony.setCaseParticipator(caseParticipatorRepository.findById(listParticipator).orElseThrow());
        testimonyRepository.save(testimony);

        return "redirect:/Detective/Testimony/TestimonyView";
    }
    @GetMapping ("/Testimony/TestimonyEdit/{id}")
    public String TestimonyEditView(@PathVariable Long id, Testimony testimony, CaseParticipator caseParticipator,
                                     Model model)
    {
        testimony= testimonyRepository.findById(id).orElseThrow();
        model.addAttribute("testimony",testimony);
        Iterable<CaseParticipator>  caseParticipators=caseParticipatorRepository.findAll();
        model.addAttribute("caseParticipators",caseParticipators);
        return "/Detective/Testimony/TestimonyEdit";
    }
    @PostMapping ("/Testimony/TestimonyEdit/{id}")
    public String TestimonyEdit(   @Valid Testimony testimony,
                                    @RequestParam Long listParticipator,
                                    BindingResult bindingResult,
                                    Model model)
    {


        if (bindingResult.hasErrors())
        {
            model.addAttribute("testimony",testimony);
            return "/Detective/Testimony/TestimonyEdit";
        }
        testimony.setCaseParticipator(caseParticipatorRepository.findById(listParticipator).orElseThrow());
        testimonyRepository.save(testimony);


        return "redirect:/Detective/Testimony/TestimonyView";
    }
    @GetMapping("/Testimony/TestimonyDetail/{id}")
    public String TestimonyDetails(@PathVariable Long id,
                                   Model model)
    {
        Testimony testimony = testimonyRepository.findById(id).orElseThrow();

        model.addAttribute("testimony",testimony);
        return "/Detective/Testimony/TestimonyDetail";
    }
    @GetMapping ("/Testimony/TestimonyDelete/{id}")
    public String TestimonyDelete(@PathVariable Long id)
    {

//        accountRepository.deleteById(id);
        testimonyRepository.deleteById(id);
        return "redirect:/Detective/Testimony/TestimonyView";
    }
    @GetMapping("/Testimony/TestimonyFilter")
    public String TestimonyFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Testimony> testimonies=testimonyRepository.findByCaseParticipator_SurnameContaining(name);
        model.addAttribute("testimonies",testimonies);
        return "/Detective/Testimony/TestimonyFilter";
    }
    @GetMapping("/Testimony/TestimonyFilterNoCategory")
    public String TestimonyFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Testimony> testimonies=testimonyRepository.findByCaseParticipator_Surname(name);
        model.addAttribute("testimonies",testimonies);
        return "/Detective/Testimony/TestimonyFilterNoCategory";
    }
    //Clue

    @GetMapping("/Clue/ClueView")
    public String Clue(Model model)
    {
        Iterable<Clue> listClue= clueRepository.findAll();
        model.addAttribute("listClue",listClue);
        return "/Detective/Clue/ClueView";
    }
    @GetMapping("/Clue/ClueAdd")
    public String ClueAddView(Model model, Clue clue)
    {
        Iterable<CrimeCase> crimeCases= crimeCaseRepository.findAll();
        model.addAttribute("crimeCases",crimeCases);
        return "/Detective/Clue/ClueAdd";
    }
    @PostMapping("/Clue/ClueAdd")
    public String ClueAdd(
            @Valid Clue clue,
            @RequestParam Long crimeCases,
            BindingResult bindingResult,
            Model model)
    {

        if(bindingResult.hasErrors())
        {
            return "/Detective/Clue/ClueAdd";
        }

        clue.setCases(crimeCaseRepository.findById(crimeCases).orElseThrow());
        clueRepository.save(clue);

        return "redirect:/Detective/Clue/ClueView";
    }
    @GetMapping ("/Clue/ClueEdit/{id}")
    public String ClueView(@PathVariable Long id, Clue clue, CrimeCase crimeCase,
                                    Model model)
    {
        clue= clueRepository.findById(id).orElseThrow();
        model.addAttribute("clue",clue);
        Iterable<CrimeCase>  crimeCases=crimeCaseRepository.findAll();
        model.addAttribute("crimeCases",crimeCases);
        return "/Detective/Clue/ClueEdit";
    }
    @PostMapping ("/Clue/ClueEdit/{id}")
    public String ClueEdit(   @Valid Clue clue,
                                   @RequestParam Long crimeCases,
                                   BindingResult bindingResult,
                                   Model model)
    {


        if (bindingResult.hasErrors())
        {
            model.addAttribute("clue",clue);
            return "/Detective/Clue/ClueEdit";
        }
        clue.setCases(crimeCaseRepository.findById(crimeCases).orElseThrow());
        clueRepository.save(clue);


        return "redirect:/Detective/Clue/ClueView";
    }
    @GetMapping("/Clue/ClueDetail/{id}")
    public String ClueDetails(@PathVariable Long id,
                                   Model model)
    {
        Clue clue = clueRepository.findById(id).orElseThrow();

        model.addAttribute("clue",clue);
        return "/Detective/Clue/ClueDetail";
    }
    @GetMapping ("/Clue/ClueDelete/{id}")
    public String ClueDelete(@PathVariable Long id)
    {

//        accountRepository.deleteById(id);
        testimonyRepository.deleteById(id);
        return "redirect:/Detective/Clue/ClueView";
    }
    @GetMapping("/Clue/ClueFilter")
    public String ClueFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Clue> clues=clueRepository.findByDescriptionContaining(name);
        model.addAttribute("clues",clues);
        return "/Detective/Clue/ClueFilter";
    }
    @GetMapping("/Clue/ClueFilterNoCategory")
    public String ClueFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Clue> clues=clueRepository.findByDescription(name);
        model.addAttribute("clues",clues);
        return "/Detective/Clue/ClueFilterNoCategory";
    }
}
