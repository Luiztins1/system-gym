package com.github.Luiztins1.repository;

import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.enums.TypePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {
    boolean existsByTypePlan(TypePlan typePlan);
}
