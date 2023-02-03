package com.example.RPD.Repository;


import com.example.RPD.Models.Adress;
import com.example.RPD.Models.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdressRepository extends CrudRepository<Adress,Long> {

    public List<Adress> findByLocationContaining(String name);
    public List<Adress> findFirstByLocation(String name);
    Adress findByLocation(String name);
}
