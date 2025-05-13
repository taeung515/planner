package com.example.planner.service;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto dto);
}
