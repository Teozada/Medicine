package com.example.medicine.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@EntityListeners(AuditingEntityListener.class)
public class Audit {
    @CreatedDate
    @CreationTimestamp
    @Column(insertable = true, updatable = false)
    private Instant createdAt = Instant.now();
    @LastModifiedDate
    @UpdateTimestamp
    @Column(insertable = false, updatable = true)
    private Instant updatedAt = Instant.now();
}
