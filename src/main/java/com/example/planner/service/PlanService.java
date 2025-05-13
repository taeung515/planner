package com.example.planner.service;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;

import java.util.Date;
import java.util.List;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto dto);

    List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, Date updatedDate);
}
