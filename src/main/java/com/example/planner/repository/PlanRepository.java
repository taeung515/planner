package com.example.planner.repository;

import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository {

    PlanResponseDto createPlan(Plan plan);

    List<PlanResponseDto> findAllPlans();

    List<PlanResponseDto> findPlanByDate(LocalDate updatedDate);

    List<PlanResponseDto> findPlanByName(String name);

    List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, LocalDate updatedDate);

    Plan findPlanByID(Long id);

    int updatePlan(Plan plan);

    int deletePlan(Long id);

}
