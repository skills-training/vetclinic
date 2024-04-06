package com.szlify.veterinaryclinic.service;

import com.szlify.veterinaryclinic.mapper.PetMapper;
import com.szlify.veterinaryclinic.model.Pet;
import com.szlify.veterinaryclinic.model.PetCommand;
import com.szlify.veterinaryclinic.model.PetDto;
import com.szlify.veterinaryclinic.repository.OwnerRepository;
import com.szlify.veterinaryclinic.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final PetMapper petMapper;

    public Page<PetDto> getAllPets(Pageable pageable) {
        Page<Pet> petPage = petRepository.findAll(pageable);

        return petPage.map(petMapper::toDto);
    }
    //użyłem tu paginacji bo uważam, że wizyt i zwierzat moze byc naprawde dużo a użytkownikow czy tez weterynarzy znacznie mniej

    public PetDto getPetById(int id) {
        return petMapper.toDto(petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet doesnt found")));
    }

    public List<PetDto> findByOwnerId(int ownerId) {
        List<Pet> pets = petRepository.findByOwnerId(ownerId);
        return pets.stream()
                .map(petMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PetDto createPet(PetCommand petCommand) {
        Pet pet = petMapper.fromCommand(petCommand);

        int ownerId = petCommand.getOwnerId();
        System.out.println("Received " + ownerId);
        pet.setOwner(ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + ownerId)));

        System.out.println("pet po " + pet);

        return petMapper.toDto(petRepository.save(pet));
    }

    @Transactional
    public void deletePet(int id) {
        petRepository.deleteById(id);
    }

    public Pet findWithLockingById(int id) {
        return petRepository.findWithLockingById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));
    }
}
