package com.example.planner.service;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;
import com.example.planner.repository.PlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
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
    public List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, LocalDate updatedDate) {

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

    @Transactional
    @Override
    public PlanResponseDto updatePlan(Long id, String name, String password, String todo) {

        if ((name == null && todo == null) || password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter your name or todo, and you must enter your password");
        }

        Plan planByID = planRepository.findPlanByID(id);

        if (!planByID.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }

        planByID.editPlan(name, todo);
        planRepository.updatePlan(planByID);

        return new PlanResponseDto(planByID);
    }

    @Override
    public void deletePlan(Long id, String password) {
        Plan planByID = planRepository.findPlanByID(id);

        if (!planByID.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }

        int deletedRow = planRepository.deletePlan(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }

}
