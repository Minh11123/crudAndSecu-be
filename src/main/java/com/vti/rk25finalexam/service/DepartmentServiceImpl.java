package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.criteria.DepartmentCriteria;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import com.vti.rk25finalexam.service.crud.AccountCrudService;
import com.vti.rk25finalexam.service.crud.DepartmentCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentCrudService departmentCrudService;
    private final AccountCrudService accountCrudService;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentCrudService departmentCrudService,
                                 AccountCrudService accountCrudService, ModelMapper modelMapper) {
        this.departmentCrudService = departmentCrudService;
        this.accountCrudService = accountCrudService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DepartmentDTO> getAll() {
        return null;
    }

    @Override
    public Optional<DepartmentDTO> getOne(Integer id) {
        return departmentCrudService.getOne(id)
                .map(department -> modelMapper.map(department, DepartmentDTO.class));
    }

    @Override
    public DepartmentDTO create(Department department) {
        return null;
    }

    @Override
    public DepartmentDTO delete(Integer id) {
        return null;
    }

    @Override
    public Page<DepartmentDTO> findAllByCriteria(DepartmentCriteria criteria, Pageable pageable) {
        return null;
    }

    @Override
    public DepartmentDTO update(Integer id, Department department) {
        return null;
    }
}
