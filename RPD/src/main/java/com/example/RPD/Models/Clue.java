package com.example.RPD.Models;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=10,max=150,message="Поле должно содержать не менeе 10 и не более 150 символов")
    private String location;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=10,max=30,message="Поле должно содержать не менeе 10 и не более 150 символов")
    private String date_of_find;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=10,max=150,message="Поле должно содержать не менeе 10 и не более 150 символов")
    private String description;

    @ManyToOne(optional =true, cascade =CascadeType.ALL)
    private CrimeCase crimeCase;

    @OneToMany(mappedBy = "clues", fetch =FetchType.EAGER)
    private Collection<Expertise> expertise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate_of_find() {
        return date_of_find;
    }

    public void setDate_of_find(String date_of_find) {
        this.date_of_find = date_of_find;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CrimeCase getCases() {
        return crimeCase;
    }

    public void setCases(CrimeCase cases) {
        this.crimeCase = cases;
    }

    public Collection<Expertise> getExpertise() {
        return expertise;
    }

    public void setExpertise(Collection<Expertise> expertise) {
        this.expertise = expertise;
    }

    public Clue() {
    }

    public Clue(String location, String date_of_find, String description, CrimeCase crimecases, Collection<Expertise> expertise) {
        this.location = location;
        this.date_of_find = date_of_find;
        this.description = description;
        this.crimeCase = crimecases;
        this.expertise = expertise;
    }
}
