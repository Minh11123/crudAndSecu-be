package com.vti.rk25finalexam.service.crud;

import com.vti.rk25finalexam.common.Constants;
import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import com.vti.rk25finalexam.entity.criteria.DepartmentCriteria;
import com.vti.rk25finalexam.repository.DepartmentRepository;

import java.util.Optional;

import com.vti.rk25finalexam.service.QueryService;
import com.vti.rk25finalexam.spec.filter.IntegerFilter;
import com.vti.rk25finalexam.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DepartmentCrudServiceImpl implements DepartmentCrudService {
    
    private final DepartmentRepository deptRepo;
    private final QueryService<Department> queryService;
    private final ModelMapper modelMapper;

    public DepartmentCrudServiceImpl(
            DepartmentRepository deptRepo, QueryService<Department> queryService, ModelMapper modelMapper) {
        this.deptRepo = deptRepo;
        this.queryService = queryService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Optional<Department> getOne(Integer id) {
        return deptRepo.findById(id);
    }

    @Override
    public Page<Department> findAllByCriteria(DepartmentCriteria criteria, Pageable pageable) {
        Specification<Department> spec = buildWhere(criteria);
        return deptRepo.findAll(spec, pageable);
    }

    @Override
    public Department delete(Integer id) {
        return null;
    }

    @Override
    public Department save(Department department) {
        return null;
    }

    @Override
    public Optional<Department> findByName(String username) {
        return Optional.empty();
    }
    private Specification<Department> buildWhere(DepartmentCriteria criteria) {
        Specification<Department> spec = Specification.where(null);

        if (criteria.getId() != null) {
            spec = spec.and(queryService.buildIntegerFilter(Constants.DEPARTMENT.ID, criteria.getId()));
        }
        if (criteria.getName() != null) {
            spec = spec.and(queryService.buildStringFilter(Constants.DEPARTMENT.NAME, criteria.getName()));
        }
        if (criteria.getTotalMember() != null) {
            spec = spec.and(queryService.buildIntegerFilter(Constants.DEPARTMENT.TOTAL_MEMBER, criteria.getTotalMember()));
        }
        if (criteria.getType() != null) {
            spec = spec.and(queryService.buildStringFilter(Constants.DEPARTMENT.TYPE, criteria.getType()));
        }
        if (criteria.getCreatedDate() != null) {
            spec = spec.and(queryService.buildStringFilter(Constants.DEPARTMENT.CREATE_DATE, criteria.getCreatedDate()));
        }
        if (criteria.getSearch() != null) {
            Specification<Department> orSpec = Specification.where(null);
            orSpec = orSpec
                    .or(queryService.buildStringFilter(Constants.DEPARTMENT.TOTAL_MEMBER, criteria.getSearch()))
                    .or(queryService.buildStringFilter(Constants.DEPARTMENT.NAME, criteria.getSearch()))
                    .or(queryService.buildStringFilter(Constants.DEPARTMENT.TYPE, criteria.getSearch()));
            spec = spec.and(orSpec);
        }
        return spec;
    }
}
