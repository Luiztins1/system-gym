package com.github.Luiztins1.model.entity;

import com.github.Luiztins1.model.enums.TypePlan;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "plans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Plan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "type_plan", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePlan typePlan;

    @OneToMany(mappedBy = "plan_id")
    private List<Student> studentList;

}
