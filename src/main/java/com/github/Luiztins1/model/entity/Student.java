package com.github.Luiztins1.model.entity;

import com.github.Luiztins1.model.enums.TypeModality;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Student extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "typeModality", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeModality typeModality;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student", referencedColumnName = "id")
    private Plan planId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
    private Registration registrationId;
}
