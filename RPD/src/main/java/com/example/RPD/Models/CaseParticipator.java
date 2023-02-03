package com.example.RPD.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
public class CaseParticipator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=30,message="Поле должно содержать не менне 3х и не более 30 символов")
    private String name;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=30,message="Поле должно содержать не менне 3х и не более 30 символов")
    private String surname;

    @Size(min=3,max=30,message="Поле должно содержать не менне 3х и не более 30 символов")
    private String patronymic;



    @ManyToOne(optional =true)
    private CaseParticipantCategory caseParticipantCategory;

    @ManyToMany
    @JoinTable(name="caseparticipator_crimecase",
            joinColumns=@JoinColumn(name = "caseparticipator_id"),
            inverseJoinColumns=@JoinColumn(name = "crimecases_id"))
    private List<CrimeCase> crimeCases;

    @OneToMany(mappedBy = "caseParticipator", fetch =FetchType.EAGER)
    private Collection<Testimony> testimonies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public CaseParticipantCategory getCaseParticipantCategory() {
        return caseParticipantCategory;
    }

    public void setCaseParticipantCategory(CaseParticipantCategory caseParticipantCategory) {
        this.caseParticipantCategory = caseParticipantCategory;
    }

    public List<CrimeCase> getCrimeCases() {
        return crimeCases;
    }

    public void setCrimeCases(List<CrimeCase> crimeCases) {
        this.crimeCases = crimeCases;
    }

    public Collection<Testimony> getTestimonies() {
        return testimonies;
    }

    public void setTestimonies(Collection<Testimony> testimonies) {
        this.testimonies = testimonies;
    }

    public CaseParticipator() {
    }

    public CaseParticipator(String name, String surname, String patronymic, CaseParticipantCategory caseParticipantCategory, List<CrimeCase> crimeCases, Collection<Testimony> testimonies) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.caseParticipantCategory = caseParticipantCategory;
        this.crimeCases = crimeCases;
        this.testimonies = testimonies;
    }
}
