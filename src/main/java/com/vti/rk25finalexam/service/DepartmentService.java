package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentDTO> getAll();

    Optional<DepartmentDTO> getOne(Integer id);

    DepartmentDTO create(Department department);

    DepartmentDTO delete(Integer id);

    DepartmentDTO update(Integer id, Department department);
}
