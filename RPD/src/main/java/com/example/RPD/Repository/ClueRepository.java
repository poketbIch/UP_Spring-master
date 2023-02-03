package com.example.RPD.Repository;


import com.example.RPD.Models.Clue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClueRepository extends CrudRepository<Clue,Long> {
    List<Clue> findByDescriptionContaining(String name);
    public List<Clue> findByDescription(String name);
}
