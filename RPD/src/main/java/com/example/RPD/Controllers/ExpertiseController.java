package com.example.RPD.Controllers;

import com.example.RPD.Models.*;
import com.example.RPD.Repository.ClueRepository;
import com.example.RPD.Repository.ExpertiseRepository;
import com.example.RPD.Repository.Expertise_ToolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/Expertise")
@PreAuthorize("hasAnyAuthority('EXPERTISE')")
public class ExpertiseController {
    @Autowired
    Expertise_ToolsRepository expertise_toolsRepository;
    @Autowired
    ExpertiseRepository expertiseRepository;
    @Autowired
    ClueRepository clueRepository;
    //Expertise_Tool

    @GetMapping("/Expertise_Tool/Expertise_ToolView")
    public String Expertise_Tool(Model model)
    {
        Iterable<ExpertiseTools> listTools= expertise_toolsRepository.findAll();
        model.addAttribute("listTools",listTools);
        return "/Expertise/Expertise_Tool/Expertise_ToolView";
    }
    @GetMapping("/Expertise_Tool/Expertise_ToolAdd")
    public String Expertise_ToolAddView(ExpertiseTools expertiseTools, Model model)
    {
//        Iterable<Expertise_Tools> expertiseTools= expertise_toolsRepository.findAll();
//        model.addAttribute("expertiseTools",expertiseTools);
        return "/Expertise/Expertise_Tool/Expertise_ToolAdd";
    }
    @PostMapping("/Expertise_Tool/Expertise_ToolAdd")
    public String Expertise_ToolAdd(
            @Valid ExpertiseTools expertiseTools,
            BindingResult bindingResult,
            Model model)
    {

        if(bindingResult.hasErrors())
        {
            return "/Expertise/Expertise_Tool/Expertise_ToolAdd";
        }
        expertise_toolsRepository.save(expertiseTools);

        return "redirect:/Expertise/Expertise_Tool/Expertise_ToolView";
    }
    @GetMapping ("/Expertise_Tool/Expertise_ToolEdit/{id}")
    public String Expertise_ToolEditView(@PathVariable Long id, ExpertiseTools expertiseTools,
                           Model model)
    {
        expertiseTools= expertise_toolsRepository.findById(id).orElseThrow();
        model.addAttribute("expertiseTools",expertiseTools);
        return "/Expertise/Expertise_Tool/Expertise_ToolEdit";
    }
    @PostMapping("/Expertise_Tool/Expertise_ToolEdit/{id}")
    public String Expertise_ToolEdit(   @Valid ExpertiseTools expertiseTools,
                              BindingResult bindingResult,
                              Model model)
    {


        if (bindingResult.hasErrors())
        {
            model.addAttribute("expertise_tools",expertiseTools);
            return "/Expertise/Expertise_Tool/Expertise_ToolEdit";
        }

        expertise_toolsRepository.save(expertiseTools);


        return "redirect:/Expertise/Expertise_Tool/Expertise_ToolView";
    }

    @GetMapping ("/Expertise_Tool/Expertise_ToolDelete/{id}")
    public String Expertise_ToolDelete(@PathVariable Long id)
    {

//        accountRepository.deleteById(id);
        expertise_toolsRepository.deleteById(id);
        return "redirect:/Expertise/Expertise_Tool/Expertise_ToolView";
    }
    @GetMapping("/Expertise_Tool/Expertise_ToolFilter")
    public String Expertise_ToolFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<ExpertiseTools> expertiseTools=expertise_toolsRepository.findByExpertisetoolContaining(name);
        model.addAttribute("expertiseTools",expertiseTools);
        return "/Expertise/Expertise_Tool/Expertise_ToolFilter";
    }
    @GetMapping("/Expertise_Tool/Expertise_ToolFilterNoCategory")
    public String Expertise_ToolFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<ExpertiseTools> expertiseTools=expertise_toolsRepository.findByExpertisetool(name);
        model.addAttribute("expertiseTools",expertiseTools);
        return "/Expertise/Expertise_Tool/Expertise_ToolFilterNoCategory";
    }
    @GetMapping("/Expertise/ExpertiseView")
    public String Expertise(Model model)
    {
        Iterable<Expertise> listExp =expertiseRepository.findAll();
        model.addAttribute("listExp",listExp);
        return "/Expertise/Expertise/ExpertiseView";
    }
    @GetMapping("/Expertise/ExpertiseAdd")
    public String ExpertiseAddView(Model model, Expertise expertise)
    {
        Iterable<ExpertiseTools> expertiseTools= expertise_toolsRepository.findAll();
        model.addAttribute("expertiseTools",expertiseTools);
        Iterable<Clue> clues= clueRepository.findAll();
        model.addAttribute("clues",clues);
        return "/Expertise/Expertise/ExpertiseAdd";
    }
    @PostMapping("/Expertise/ExpertiseAdd")
    public String ExpertiseAdd(
            @Valid Expertise expertise,
            @RequestParam Long expertiseTools,
            @RequestParam Long clues,
            BindingResult bindingResult,
            Model model)
    {

        if(bindingResult.hasErrors())
        {
            model.addAttribute("expertise",expertise);
            return "/Expertise/Expertise/ExpertiseAdd";
        }
        expertise.setExpertise_tools(expertise_toolsRepository.findById(expertiseTools).orElseThrow());
        expertise.setClues(clueRepository.findById(clues).orElseThrow());
        expertiseRepository.save(expertise);

        return "redirect:/Expertise/Expertise/ExpertiseView";
    }
    @GetMapping("/Expertise/ExpertiseDetail/{id}")
    public String ExpertiseDetails(@PathVariable Long id,
                                   Model model)
    {
        Expertise expertise = expertiseRepository.findById(id).orElseThrow();

        model.addAttribute("expertise",expertise);
        return "/Expertise/Expertise/ExpertiseDetail";
    }
    @GetMapping ("/Expertise/ExpertiseEdit/{id}")
    public String ExpertiseEditView(@PathVariable Long id, Expertise expertise, Clue clue,ExpertiseTools expertiseTools,
                                    Model model)
    {
        expertise= expertiseRepository.findById(id).orElseThrow();
        model.addAttribute("expertise",expertise);
        Iterable<Clue>  clues=clueRepository.findAll();
        model.addAttribute("clues",clues);
        Iterable<ExpertiseTools>  expertiseTool=expertise_toolsRepository.findAll();
        model.addAttribute("expertiseTool",expertiseTool);
        return "/Expertise/Expertise/ExpertiseEdit";
    }
    @PostMapping ("/Expertise/ExpertiseEdit/{id}")
    public String ExpertiseEdit(   @Valid Expertise expertise,
                                   @RequestParam Long expertiseTool,
                                   @RequestParam Long clues,
                                   BindingResult bindingResult,
                                   Model model)
    {


        if (bindingResult.hasErrors())
        {
            model.addAttribute("expertise",expertise);
            return "/Expertise/Expertise/ExpertiseEdit";
        }
        expertise.setExpertise_tools(expertise_toolsRepository.findById(expertiseTool).orElseThrow());
        expertise.setClues(clueRepository.findById(clues).orElseThrow());
        expertiseRepository.save(expertise);



        return "redirect:/Expertise/Expertise/ExpertiseView";
    }
    @GetMapping("/Expertise/ExpertiseFilter")
    public String ExpertiseFilter(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Expertise> expertiseFilter=expertiseRepository.findByResultContaining(name);
        model.addAttribute("expertiseFilter",expertiseFilter);
        return "/Expertise/Expertise/ExpertiseFilter";
    }
    @GetMapping("/Expertise/ExpertiseFilterNoCategory")
    public String ExpertiseFilterNoCategory(
            @RequestParam(name="search_name") String name,
            Model model)
    {
        List<Expertise> expertiseFilter=expertiseRepository.findByResult(name);
        model.addAttribute("expertiseFilter",expertiseFilter);
        return "/Expertise/Expertise/ExpertiseFilterNoCategory";
    }
    @GetMapping ("/Expertise/ExpertiseDelete/{id}")
    public String ExpertiseDelete(@PathVariable Long id)
    {
        expertiseRepository.deleteById(id);
        return "redirect:/Expertise/Expertise/ExpertiseView";
    }
}
