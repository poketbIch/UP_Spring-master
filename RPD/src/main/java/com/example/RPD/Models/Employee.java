package com.example.RPD.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=30,message="Поле должно содержать не менее 3х и не более 30 символов")
    private String name;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=30,message="Поле должно содержать не менее 3х и не более 30 символов")
    private String surname;

    @NotBlank(message="Пробелы недопустимы")
    private String patronymic;
    @ManyToMany
    @JoinTable(name="employee_adress",
            joinColumns=@JoinColumn(name = "employee_id"),
            inverseJoinColumns=@JoinColumn(name = "adress_id"))
    private List<Adress> adresses;
    @ManyToOne(optional =true)
    private Dolj dolj;
    @ManyToOne(optional =true)
    private Department department;


    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Account account;

    @OneToMany(mappedBy = "employee", fetch =FetchType.LAZY)
    private Collection<CrimeCase> cases;

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

    public List<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }

    public Dolj getDolj() {
        return dolj;
    }

    public void setDolj(Dolj dolj) {
        this.dolj = dolj;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Collection<CrimeCase> getCases() {
        return cases;
    }

    public void setCases(Collection<CrimeCase> cases) {
        this.cases = cases;
    }

    public Employee() {
    }

    public Employee(String name, String surname, String patronymic, List<Adress> adresses, Dolj dolj, Department department, Account account, Collection<CrimeCase> cases) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.adresses = adresses;
        this.dolj = dolj;
        this.department = department;
        this.account = account;
        this.cases = cases;
    }
}
