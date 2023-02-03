package com.example.RPD.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private  String username;
    private  String password;
    private  Boolean active;
    private String email;
    @ElementCollection(targetClass = Role.class,
            fetch = FetchType.EAGER)
    @CollectionTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
    @OneToOne(optional = true,mappedBy = "account")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Account() {
    }

    public Account(String username, String password, Boolean active, String email, Set<Role> role, Employee employee) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.email = email;
        this.role = role;
        this.employee = employee;
    }
}
