package com.example.RPD.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
public class CrimeCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Positive(message = "Значение не должно быть негативным")
    @NotNull
    private int number_of_case;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=10,max=150,message="Поле должно содержать не менeе 10 и не более 150 символов")
    private String description;

    @NotEmpty(message="Поле не должно быть пустым")
    private String date_of_case;

    @OneToMany(mappedBy = "crimeCase", fetch =FetchType.LAZY)
    private Collection<Clue> clues;

    @ManyToMany
    @JoinTable(name="caseparticipator_crimecase",
            joinColumns=@JoinColumn(name = "crimecases_id"),
            inverseJoinColumns=@JoinColumn(name = "caseparticipator_id"))
    private List<CaseParticipator> caseparticipators;
    @ManyToOne(optional =true)
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumber_of_case() {
        return number_of_case;
    }

    public void setNumber_of_case(int number_of_case) {
        this.number_of_case = number_of_case;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_of_case() {
        return date_of_case;
    }

    public void setDate_of_case(String date_of_case) {
        this.date_of_case = date_of_case;
    }

    public Collection<Clue> getClues() {
        return clues;
    }

    public void setClues(Collection<Clue> clues) {
        this.clues = clues;
    }

    public List<CaseParticipator> getCaseParticipators() {
        return caseparticipators;
    }

    public void setCaseParticipators(List<CaseParticipator> caseParticipators) {
        caseparticipators = caseParticipators;
    }

    public CrimeCase() {
    }

    public CrimeCase(Employee employee, int number_of_case, String description, String date_of_case, Collection<Clue> clues, List<CaseParticipator> caseParticipators) {
        this.employee = employee;
        this.number_of_case = number_of_case;
        this.description = description;
        this.date_of_case = date_of_case;
        this.clues = clues;
        caseparticipators = caseParticipators;
    }
}
