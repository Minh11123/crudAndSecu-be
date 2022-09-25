package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import com.vti.rk25finalexam.entity.criteria.DepartmentCriteria;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentDTO> getAll();

    Optional<DepartmentDTO> getOne(Integer id);

    DepartmentDTO create(Department department);

    DepartmentDTO delete(Integer id);

    Page<DepartmentDTO> findAllByCriteria(DepartmentCriteria criteria, Pageable pageable);

    DepartmentDTO update(Integer id, Department department);
}
