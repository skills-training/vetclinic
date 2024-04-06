package com.szlify.veterinaryclinic.repository;

import com.szlify.veterinaryclinic.model.Vet;
import com.szlify.veterinaryclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {

    List<Visit> findAllByVet(Vet vet);

    boolean existsAllByVetIdAndAndDateTime(int vetId, LocalDateTime localDateTime);
}
