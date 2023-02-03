package com.example.RPD.Repository;

import com.example.RPD.Models.Dolj;
import com.example.RPD.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoljRepository extends CrudRepository<Dolj,Long> {
    public List<Dolj> findByNameContaining(String name);
    public List<Dolj> findByName(String name);
}
