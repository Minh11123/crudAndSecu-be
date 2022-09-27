package com.vti.rk25finalexam.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
//import org.springframework.hateoas.RepresentationModel;


//@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentDTO{

    private Integer id;

    private String name;

    private Integer totalMember;

    private String type;

    private Integer isDeleted;

    public DepartmentDTO isDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    @JsonIgnore
    private String password;

    private LocalDateTime createdDate;


    public DepartmentDTO id(Integer id) {
        this.id = id;
        return this;
    }

    public DepartmentDTO name(String name) {
        this.name = name;
        return this;
    }
    public DepartmentDTO totalMember(Integer totalMember) {
        this.totalMember = totalMember;
        return this;
    }


    public DepartmentDTO type(String type) {
        this.type = type;
        return this;
    }
}
