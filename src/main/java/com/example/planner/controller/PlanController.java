package com.example.planner.controller;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;
import com.example.planner.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;


    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<PlanResponseDto> createPlan(@RequestBody PlanRequestDto dto) {
        return new ResponseEntity<>(planService.createPlan(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findPlanByNameOrUpdatedDate(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate updatedDate
    ) {
        return new ResponseEntity<>(planService.findPlanByNameOrUpdatedDate(name, updatedDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findPlanById(@PathVariable Long id) {
        return new ResponseEntity<>(planService.findPlanById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updatePlan(
            @PathVariable Long id,
            @RequestBody PlanRequestDto dto
    ) {
        return new ResponseEntity<>(planService.updatePlan(id, dto.getName(), dto.getPassword(), dto.getTodo()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(
            @PathVariable Long id,
            @RequestBody PlanRequestDto dto
    ) {
        planService.deletePlan(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
