package com.example.RPD.Repository;


import com.example.RPD.Models.ExpertiseTools;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Expertise_ToolsRepository extends CrudRepository<ExpertiseTools,Long> {
    List <ExpertiseTools> findByExpertisetoolContaining(String name);
    List <ExpertiseTools> findByExpertisetool(String name);
}
