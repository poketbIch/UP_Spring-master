package com.example.RPD.Repository;


import com.example.RPD.Models.Department;
import com.example.RPD.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department,Long> {
    public List<Department> findByLocationContaining(String name);
    public List<Department> findByLocation(String name);
}
