package com.example.RPD.Models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "expertisetools")
public class ExpertiseTools {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min=3,max=30,message="Поле должно содержать не менее 3х и не более 30 символов")
    private String expertisetool;
    @OneToMany(mappedBy = "expertise_tools", fetch =FetchType.EAGER)
    private Collection<Expertise> expertise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpertisetool() {
        return expertisetool;
    }

    public void setExpertisetool(String expertisetool) {
        this.expertisetool = expertisetool;
    }

    public Collection<Expertise> getExpertise() {
        return expertise;
    }

    public void setExpertise(Collection<Expertise> expertise) {
        this.expertise = expertise;
    }

    public ExpertiseTools() {
    }

    public ExpertiseTools(String expertisetool, Collection<Expertise> expertise) {
        this.expertisetool = expertisetool;
        this.expertise = expertise;
    }
}
