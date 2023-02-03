package com.example.RPD.Repository;


import com.example.RPD.Models.CaseParticipantCategory;
import com.example.RPD.Models.CrimeCase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CaseParticipantCategoryRepository extends CrudRepository<CaseParticipantCategory,Long> {
    public List<CaseParticipantCategory> findByCategoryContaining(String name);
    public List<CaseParticipantCategory> findByCategory(String name);
}
