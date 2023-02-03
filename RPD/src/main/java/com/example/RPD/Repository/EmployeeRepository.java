package com.example.RPD.Repository;


import com.example.RPD.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    public List<Employee> findByName(String name);
    Employee findFirstByName(String name);
    public List<Employee> findByNameContaining(String name);
}
