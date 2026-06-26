package com.github.Luiztins1.model.entity;

import com.github.Luiztins1.model.enums.TypeModality;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "registrations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Registration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "modality", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeModality modality;

    @Column(name = "registration_date", nullable = false)
    private Instant registration_date;

    @OneToOne(mappedBy = "registration_id")
    private Student student;
}
