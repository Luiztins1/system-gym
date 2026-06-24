package com.github.Luiztins1.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
}
