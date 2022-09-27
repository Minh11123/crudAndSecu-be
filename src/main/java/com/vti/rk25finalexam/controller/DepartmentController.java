package com.vti.rk25finalexam.controller;

import com.vti.rk25finalexam.entity.Department;
import com.vti.rk25finalexam.entity.criteria.DepartmentCriteria;
import com.vti.rk25finalexam.entity.dto.DepartmentCreateDTO;
import com.vti.rk25finalexam.entity.dto.DepartmentDTO;
import com.vti.rk25finalexam.entity.dto.DepartmentUpdateDTO;
import com.vti.rk25finalexam.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.NotActiveException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {

    @Autowired
    private  DepartmentService departmentService;


    @GetMapping()
    public ResponseEntity<Page<DepartmentDTO>> getAll(
            DepartmentCriteria departmentCriteria,
            Pageable pageable
    ){
        Page<DepartmentDTO> DepartmentList = departmentService.findAllByCriteria(departmentCriteria,pageable);
        return ResponseEntity
            .ok()
            .body(DepartmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DepartmentDTO>> getOne(@PathVariable Integer id)throws NotFoundException {
        Optional<DepartmentDTO> departmentDTO = departmentService.getOne(id);
        return ResponseEntity
            .ok()
            .body(departmentDTO);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentCreateDTO departmentCreateDTO) {
        DepartmentDTO departmentDTO = departmentService.create(departmentCreateDTO);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(departmentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> update(
        @PathVariable Integer id,
        @RequestBody DepartmentUpdateDTO departmentUpdateDTO
    ){
        DepartmentDTO departmentDTO = departmentService.update(id,departmentUpdateDTO);
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
