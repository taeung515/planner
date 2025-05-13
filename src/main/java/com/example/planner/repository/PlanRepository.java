package com.example.planner.repository;

import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;

import java.util.Date;
import java.util.List;

public interface PlanRepository {

    PlanResponseDto createPlan(Plan plan);

    List<PlanResponseDto> findAllPlans();

    List<PlanResponseDto> findPlanByDate(Date updatedDate);

    List<PlanResponseDto> findPlanByName(String name);

    List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, Date updatedDate);

    Plan findPlanByID(Long id);

    int updatePlan(Long id, String name, String todo);

    int deletePlan(Long id);
}
