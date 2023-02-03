package com.example.RPD.Repository;


import com.example.RPD.Models.CrimeCase;
import com.example.RPD.Models.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrimeCaseRepository extends CrudRepository<CrimeCase,Long> {
    public List<CrimeCase> findByDescriptionContaining(String name);
    public List<CrimeCase> findByDescription(String name);
    CrimeCase findCrimeCaseByDescription(String name);
}
