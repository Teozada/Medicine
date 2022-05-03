package com.example.medicine.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medicine")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medicine extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @OneToMany
    private List<Reactions> reactions;

    @Column(nullable = false)
    private String name;

    @Column(name = "expiration_date",nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String producer;

    public Medicine(String name,LocalDate expirationDate,String phone,Double price,int amount,String producer) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.phone = phone;
        this.price = price;
        this.amount = amount;
        this.producer = producer;
    }
}
