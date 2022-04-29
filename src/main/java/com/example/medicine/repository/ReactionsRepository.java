package com.example.medicine.repository;

import com.example.medicine.model.Reactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionsRepository extends JpaRepository<Reactions, Long> {
}
