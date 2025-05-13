package com.example.planner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Plan {

    private Long id;
    private String name;
    private String password;
    private String todo;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Plan(String name, String password, String todo) {
        this.name = name;
        this.password = password;
        this.todo = todo;
    }

}
