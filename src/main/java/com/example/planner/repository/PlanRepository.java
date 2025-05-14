package com.example.planner.repository;

import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository {
    // dto 10개 있는데 가져다 쓰는거 어려움 레포지토리에선 엔티티로 받아서 서비스계층에서 dto를 정해줌

    PlanResponseDto createPlan(Plan plan);

    List<PlanResponseDto> findAllPlans();

    List<PlanResponseDto> findPlanByDate(LocalDate updatedDate);

    List<PlanResponseDto> findPlanByName(String name);

    List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, LocalDate updatedDate);

    Plan findPlanByID(Long id);

    int updatePlan(Long id, String name, String todo);

    int deletePlan(Long id);

}
