package com.github.Luiztins1.controller.rest;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.mapper.PlanMapper;
import com.github.Luiztins1.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<PlanDTO> registerPlan(PlanDTO planDTO){
        Plan plan = planService.registerPlan(planDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(plan.getId())
                .toUri();

        return ResponseEntity.created(location).body(PlanMapper.toDto(plan));
    }

    @GetMapping
    public ResponseEntity<List<PlanDTO>> findAll(){
        List<PlanDTO> planList = planService.findAll()
                .stream()
                .map(PlanMapper::toDto)
                .toList();

        if(planList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(planList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> updatePlan(@PathVariable UUID id, @RequestBody PlanDTO planDTO ){
        Optional<Plan> planOptional = planService.updatePlan(planDTO);

        if(planOptional.isPresent()){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelRegisterPlan(@PathVariable UUID id){
        planService.cancelRegisterPlan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> findById(@PathVariable UUID id){
        return planService.findById(id)
                .map(PlanMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
