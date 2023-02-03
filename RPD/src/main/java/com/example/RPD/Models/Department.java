package com.example.RPD.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=10,max=150,message="Поле должно содержать не менeе 10 и не более 150 символов")
    private String location;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=50,message="Поле должно содержать не менeе 3 и не более 50 символов")
    private String country;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=50,message="Поле должно содержать не менeе 3 и не более 50 символов")
    private String city;
    @OneToMany(mappedBy = "department", fetch =FetchType.EAGER)
    private Collection<Employee> employees;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public Department() {
    }

    public Department(String location, String country, String city, Collection<Employee> employees) {
        this.location = location;
        this.country = country;
        this.city = city;
        this.employees = employees;
    }
}
