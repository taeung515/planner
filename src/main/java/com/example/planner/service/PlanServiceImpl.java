package com.example.planner.service;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;
import com.example.planner.repository.PlanRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService{

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public PlanResponseDto createPlan(PlanRequestDto dto) {

        Plan plan = new Plan(dto.getName(), dto.getPassword(), dto.getTodo());

        return planRepository.createPlan(plan);
    }
}
