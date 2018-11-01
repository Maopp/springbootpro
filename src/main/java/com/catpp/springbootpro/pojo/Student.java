package com.catpp.springbootpro.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
public class Student {
    @Id
    private Integer id;

    private String name;

    @Min(18)
    private Integer age;

    private String stuClass;

}