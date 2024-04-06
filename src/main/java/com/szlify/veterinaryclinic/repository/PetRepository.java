package com.szlify.veterinaryclinic.repository;

import com.szlify.veterinaryclinic.model.Pet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findByOwnerId(int id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Pet> findWithLockingById(int id);
}
