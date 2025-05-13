package com.example.planner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class PlanResponseDto {

    private Long id;
    private String name;
    private String todo;

    private Date createdDate;
    private Date updatedDate;

    public PlanResponseDto(String name, String todo, Date updatedDate) {
        this.name = name;
        this.todo = todo;
        this.updatedDate = updatedDate;
    }

}
