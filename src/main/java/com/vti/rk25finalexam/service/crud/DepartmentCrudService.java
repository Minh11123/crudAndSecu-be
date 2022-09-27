package com.vti.rk25finalexam.service.crud;

import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import com.vti.rk25finalexam.entity.criteria.DepartmentCriteria;
import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentCrudService {

    Optional<Department> getOne(Integer id);

    Page<Department> findAllByCriteria(DepartmentCriteria criteria, Pageable pageable);

    Department save(Department department);

    Optional<Department> findByName(String username);

    Optional<Department> deleteById(Integer id);
}
