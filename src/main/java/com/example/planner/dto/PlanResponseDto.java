package com.example.planner.dto;

import com.example.planner.entity.Plan;
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

    public PlanResponseDto(Plan plan) {
        this.id = plan.getId();
        this.name = plan.getName();
        this.todo = plan.getTodo();
        this.createdDate = plan.getCreatedDate();
        this.updatedDate = plan.getUpdatedDate();
    }

}
