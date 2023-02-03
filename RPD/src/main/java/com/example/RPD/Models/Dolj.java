package com.example.RPD.Models;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Dolj {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=5,max=50,message="Поле должно содержать не менее 5 и не более 50 символов")
    private String name;
    @Positive(message = "Значение не должно быть негативным")
    @NotNull
    private int salary;
    @OneToMany(mappedBy = "dolj", fetch =FetchType.EAGER)
    private Collection<Employee> employee;

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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Collection<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Collection<Employee> employee) {
        this.employee = employee;
    }

    public Dolj() {
    }

    public Dolj(String name, int salary, Collection<Employee> employee) {
        this.name = name;
        this.salary = salary;
        this.employee = employee;
    }
}
