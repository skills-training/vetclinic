package com.szlify.veterinaryclinic.repository;

import com.szlify.veterinaryclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    List<Owner> findOwnersByPetsIsNotEmpty();
}
