package com.github.Luiztins1.model.entity;

import com.github.Luiztins1.model.enums.TypePlan;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "plans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "value",  nullable = false)
    private BigDecimal value;

    @Column(name = "type_plan", nullable = false)
    private TypePlan typePlan;

    @OneToMany(mappedBy = "plan_id")
    private Student student_id;


}
