package com.example.medicine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reactions extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(nullable = false)
    private String description;

    public Reactions(String description) {
        this.description = description;
    }
}
