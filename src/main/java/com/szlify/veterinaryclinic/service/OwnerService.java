package com.szlify.veterinaryclinic.service;

import com.szlify.veterinaryclinic.mapper.OwnerMapper;
import com.szlify.veterinaryclinic.model.Owner;
import com.szlify.veterinaryclinic.model.OwnerCommand;
import com.szlify.veterinaryclinic.model.OwnerDto;
import com.szlify.veterinaryclinic.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public List<OwnerDto> getAllOwners(Boolean withPets) {
        return withPets != null && withPets ?
                ownerRepository.findOwnersByPetsIsNotEmpty().stream()
                        .map(ownerMapper::toDto)
                        .toList() :
                ownerRepository.findAll().stream()
                        .map(ownerMapper::toDto)
                        .toList();
//        if (withPets != null && withPets) {
//            return ownerRepository.findOwnersByPetsIsNotEmpty().stream()
//                    .map(ownerMapper::toDto)
//                    .toList();
//        } else {
//            return ownerRepository.findAll().stream()
//                    .map(ownerMapper::toDto)
//                    .toList();
//        może być paginacja, adam mowił że zależy od ilości rekordów
    }


    public OwnerDto findById(int id) {
        return ownerMapper.toDto(ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found")));
    }

    @Transactional
    public OwnerDto createOwner(OwnerCommand ownerCommand) {
        Owner owner = ownerMapper.fromCommand(ownerCommand);
        return ownerMapper.toDto(ownerRepository.save(owner));
    }

    @Transactional
    public void deleteById(int id) {
        ownerRepository.deleteById(id);
    }
}