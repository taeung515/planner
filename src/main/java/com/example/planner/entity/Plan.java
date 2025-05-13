package com.example.planner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Plan {

    private Long id;
    private String name;
    private String password;
    private String todo;

    private Date createdDate;
    private Date updatedDate;

    public Plan(String name, String password, String todo) {
        this.name = name;
        this.password = password;
        this.todo = todo;
    }

    public Plan(String name, Date updatedDate) {
        this.name = name;
        this.updatedDate = updatedDate;
    }
}
