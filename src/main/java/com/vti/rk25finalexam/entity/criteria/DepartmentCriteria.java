package com.vti.rk25finalexam.entity.criteria;

import com.vti.rk25finalexam.spec.filter.IntegerFilter;
import com.vti.rk25finalexam.spec.filter.StringFilter;
import lombok.Data;

@Data
public class DepartmentCriteria {

    private IntegerFilter id;

    private StringFilter name;

    private IntegerFilter totalMember;

    private StringFilter type;

    private StringFilter createdDate;

    private StringFilter search;

}
