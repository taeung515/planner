package com.example.planner.service;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto dto);

    List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, LocalDate updatedDate);

    PlanResponseDto findPlanById(Long id);

    PlanResponseDto updatePlan(Long id, String name, String password, String todo);

    void deletePlan(Long id, String password);
}
