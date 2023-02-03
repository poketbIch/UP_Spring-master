package com.example.RPD.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Expertise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min=3,max=30,message="Поле должно содержать не менне 3х и не более 30 символов")
    private String result;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=10,max=30,message="Поле должно содержать не менeе 10 и не более 150 символов")
    private String date_of_expertise;
    @ManyToOne(optional =true, cascade =CascadeType.ALL)
    private Clue clues;
    @ManyToOne(optional =true, cascade =CascadeType.ALL)
    private ExpertiseTools expertise_tools;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate_of_expertise() {
        return date_of_expertise;
    }

    public void setDate_of_expertise(String date_of_expertise) {
        this.date_of_expertise = date_of_expertise;
    }

    public Clue getClues() {
        return clues;
    }

    public void setClues(Clue clues) {
        this.clues = clues;
    }

    public ExpertiseTools getExpertise_tools() {
        return expertise_tools;
    }

    public void setExpertise_tools(ExpertiseTools expertise_tools) {
        this.expertise_tools = expertise_tools;
    }

    public Expertise() {
    }

    public Expertise(String result, String date_of_expertise, Clue clues, ExpertiseTools expertise_tools) {
        this.result = result;
        this.date_of_expertise = date_of_expertise;
        this.clues = clues;
        this.expertise_tools = expertise_tools;
    }
}
