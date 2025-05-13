package com.example.planner.service;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;

import java.util.Date;
import java.util.List;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto dto);

    List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, Date updatedDate);

    PlanResponseDto findPlanById(Long id);

    PlanResponseDto updatePlan(Long id, String name, String password, String todo);

    void deletePlan(Long id);
}
