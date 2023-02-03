package com.example.RPD.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class CaseParticipantCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message="Поле не должно быть пустым")
    @Size(min=3,max=30,message="Поле должно содержать не менне 3х и не более 30 символов")
    private String category;

    @OneToMany(mappedBy = "caseParticipantCategory", fetch =FetchType.EAGER)
    private Collection<CaseParticipator> caseParticipators;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Collection<CaseParticipator> getCaseParticipators() {
        return caseParticipators;
    }

    public void setCaseParticipators(Collection<CaseParticipator> caseParticipators) {
        this.caseParticipators = caseParticipators;
    }

    public CaseParticipantCategory() {
    }

    public CaseParticipantCategory(String category, Collection<CaseParticipator> caseParticipators) {
        this.category = category;
        this.caseParticipators = caseParticipators;
    }
}
