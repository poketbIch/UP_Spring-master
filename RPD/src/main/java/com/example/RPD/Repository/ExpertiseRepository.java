package com.example.RPD.Repository;

import com.example.RPD.Models.Expertise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpertiseRepository extends CrudRepository<Expertise,Long> {
    List<Expertise> findByResultContaining(String name);
    List<Expertise> findByResult(String name);
}
