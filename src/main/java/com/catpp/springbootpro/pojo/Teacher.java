package com.catpp.springbootpro.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
public class Teacher {
    @Id
    private Integer id;

    private String name;

    private Integer age;

}