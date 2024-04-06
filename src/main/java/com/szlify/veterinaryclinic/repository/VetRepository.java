package com.szlify.veterinaryclinic.repository;

import com.szlify.veterinaryclinic.model.Vet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface VetRepository extends JpaRepository<Vet, Integer> {

    List<Vet> findAllByVisitsIsNotEmpty();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Vet> findWithLockingById(int id);

    Optional<Vet> findByDeletedFalseAndEmail(String email);
}
