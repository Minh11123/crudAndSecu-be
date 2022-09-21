package com.vti.rk25finalexam.controller;

import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import com.vti.rk25finalexam.service.DepartmentService;
import com.vti.rk25finalexam.service.crud.DepartmentCrudService;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<DepartmentDTO> DepartmentList = departmentService.getAll();
        return ResponseEntity
            .ok()
            .body(DepartmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DepartmentDTO>> getOne(@PathVariable Integer id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getOne(id);
        return ResponseEntity
            .ok()
            .body(departmentDTO);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> create(@RequestBody Department department) {
        DepartmentDTO departmentDTO = departmentService.create(department);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(departmentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> update(
        @PathVariable Integer id,
        @RequestBody Department Department
    )
        throws NotFoundException {
        DepartmentDTO departmentDTO = departmentService.update(id, Department);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(departmentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDTO> delete(@PathVariable Integer id)
        throws NotFoundException {
        DepartmentDTO departmentDTO = departmentService.delete(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(departmentDTO);
    }

}
