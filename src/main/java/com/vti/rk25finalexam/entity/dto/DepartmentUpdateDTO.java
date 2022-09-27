package com.vti.rk25finalexam.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DepartmentUpdateDTO {

    private String name;

    private Integer totalMember;

    private String type;

    private LocalDateTime createdDate;

    private Integer isDeleted;
}
