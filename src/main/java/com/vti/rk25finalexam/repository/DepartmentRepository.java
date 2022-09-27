package com.vti.rk25finalexam.repository;

import com.vti.rk25finalexam.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface DepartmentRepository
    extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {

    Optional<Department> deleteDepartmentById(Integer id);

    Optional<Department> findDepartmentByName(String name);

}
