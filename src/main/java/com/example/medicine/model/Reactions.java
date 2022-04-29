package com.example.medicine.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
@Data
public class Reactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;
}
