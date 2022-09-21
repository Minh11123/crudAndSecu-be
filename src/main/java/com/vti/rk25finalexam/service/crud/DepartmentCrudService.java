package com.vti.rk25finalexam.service.crud;

import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface DepartmentCrudService {
    List<Department> getAll();

    Optional<Department> getOne(Integer id);

    Department save(Department department);

    Department delete(Integer id);
}
