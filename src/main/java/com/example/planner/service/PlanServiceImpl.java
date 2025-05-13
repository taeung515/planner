package com.example.planner.service;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;
import com.example.planner.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public PlanResponseDto createPlan(PlanRequestDto dto) {

        Plan plan = new Plan(dto.getName(), dto.getPassword(), dto.getTodo());

        return planRepository.createPlan(plan);
    }

    @Override
    public List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, Date updatedDate) {

        if (name != null && updatedDate != null) {

            return planRepository.findPlanByNameOrUpdatedDate(name, updatedDate);

        } else if (name == null && updatedDate != null) {

            return planRepository.findPlanByDate(updatedDate);

        } else if (name != null && updatedDate == null) {

            return planRepository.findPlanByName(name);

        } else {

            return planRepository.findAllPlans();

        }
    }

    @Override
    public PlanResponseDto findPlanById(Long id) {
        Plan planByID = planRepository.findPlanByID(id);
        return new PlanResponseDto(planByID);
    }
}
