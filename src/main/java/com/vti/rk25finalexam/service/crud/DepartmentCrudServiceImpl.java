package com.vti.rk25finalexam.service.crud;

import com.vti.rk25finalexam.common.Constants;
import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.repository.DepartmentRepository;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentCrudServiceImpl implements DepartmentCrudService {
    
    private final DepartmentRepository deptRepo;

    public DepartmentCrudServiceImpl(
            DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }

    @Override
    public List<Department> getAll() {
        return deptRepo.findAll();
    }

    @Override
    public Optional<Department> getOne(Integer id) {
        return deptRepo.findById(id);
    }

    @Override
    public Department save(Department department) {
        return deptRepo.save(department);
    }

    @Override
    public Department delete(Integer id)  {
        return getOne(id)
                .map(department -> {
                    department.id(id);
                    department.isDeleted(Constants.IS_DELETED.TRUE);
                    deptRepo.save(department);
                    return department;
                })
                .orElse(null);
    }
}
