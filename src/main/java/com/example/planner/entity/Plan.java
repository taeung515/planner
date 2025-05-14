package com.example.planner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

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

    public void editPlan(String name, String todo) {
        if (Strings.isNotBlank(name)) {
            this.name = name;
        }
        if (Strings.isNotBlank(todo)) {
            this.todo = todo;
        }
    }
}
