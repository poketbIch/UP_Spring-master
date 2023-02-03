package com.example.RPD.Repository;


import com.example.RPD.Models.Testimony;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestimonyRepository extends CrudRepository<Testimony,Long> {
    List<Testimony> findByCaseParticipator_SurnameContaining(String name);
    List<Testimony> findByCaseParticipator_Surname(String name);
}
