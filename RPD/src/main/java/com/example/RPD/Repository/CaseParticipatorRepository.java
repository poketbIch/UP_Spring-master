package com.example.RPD.Repository;


import com.example.RPD.Models.CaseParticipantCategory;
import com.example.RPD.Models.CaseParticipator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CaseParticipatorRepository extends CrudRepository<CaseParticipator,Long> {
    public List<CaseParticipator> findByNameContaining(String name);
    public  List<CaseParticipator> findByName(String name);
    CaseParticipator findBySurname(String name);
}
