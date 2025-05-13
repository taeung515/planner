package com.example.planner.repository;

import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;

public interface PlanRepository {

    PlanResponseDto createPlan(Plan plan);

}
