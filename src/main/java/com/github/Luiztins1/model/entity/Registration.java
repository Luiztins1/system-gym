package com.github.Luiztins1.model.entity;

import com.github.Luiztins1.model.enums.TypeModality;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "registrations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "modality", nullable = false)
    private TypeModality modality;

    @OneToMany(mappedBy = "registration_id")
    private Student student_id;
}
