package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.criteria.DepartmentCriteria;
import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.DepartmentCreateDTO;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import com.vti.rk25finalexam.entity.dto.DepartmentUpdateDTO;
import com.vti.rk25finalexam.exception.RK25Exception;
import com.vti.rk25finalexam.exception.Rk25Error;
import com.vti.rk25finalexam.service.crud.AccountCrudService;
import com.vti.rk25finalexam.service.crud.DepartmentCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<DepartmentDTO> getOne(Integer id) {
        return departmentCrudService.getOne(id)
                .map(department -> modelMapper.map(department, DepartmentDTO.class));
    }

    @Override
    @Transactional
    public DepartmentDTO create(DepartmentCreateDTO departmentCreateDTO) {
        validateCreate(departmentCreateDTO);
        Department department = modelMapper.map(departmentCreateDTO, Department.class);
        departmentCrudService.save(department);
        return modelMapper.map(department,DepartmentDTO.class);
    }
    private void validateCreate(DepartmentCreateDTO departmentCreateDTO) {
        validateDepartmentName(departmentCreateDTO.getName());
    }

    private void validateDepartmentName(String name) {
        if(findByName(name).isPresent())
                throw new RK25Exception()
                        .rk25Error(new Rk25Error()
                                .code("department.name.isExisted")
                                .param(name));
    }
    @Override
    public DepartmentDTO delete(Integer id) {
        return null;
    }

    @Override
    public Page<DepartmentDTO> findAllByCriteria(DepartmentCriteria criteria, Pageable pageable) {
        Page<Department> page = departmentCrudService.findAllByCriteria(criteria,pageable);
        List<DepartmentDTO> departmentDTOList = page.getContent()
                .stream()
                .map(account -> modelMapper.map(account, DepartmentDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(departmentDTOList,pageable,page.getTotalElements());
    }

    @Override
    public DepartmentDTO update(Integer id, DepartmentUpdateDTO departmentUpdateDTO) {
        return getOne(id)
                .map(departmentDTO -> modelMapper.map(departmentDTO,Department.class))
                .map(account -> account.id(id))
                .map(departmentCrudService::save)
                .map(department -> modelMapper.map(department,DepartmentDTO.class))
                .orElseThrow(() -> new RK25Exception()
                        .rk25Error(new Rk25Error()
                                .code("department.departmentId.isNotExisted")
                                .param(id)));
    }

    @Override
    public Optional<Department> findByName(String name) {
        return departmentCrudService.findByName(name);
    }


}
