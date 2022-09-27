package com.vti.rk25finalexam.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentCreateDTO {

    private String name;

    private Integer totalMember;

    private String type;

    private LocalDateTime createdDate;

    private Integer isDeleted;
}
