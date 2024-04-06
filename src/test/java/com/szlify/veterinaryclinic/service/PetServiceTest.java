package com.szlify.veterinaryclinic.service;

import com.szlify.veterinaryclinic.model.Pet;
import com.szlify.veterinaryclinic.model.PetDto;
import com.szlify.veterinaryclinic.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    void getAllPets() {
    }

    @Test
    void getPetById() {
        PetDto pet = PetDto.builder()
                .id(1)
                .name("Gandzia")
                .build();
        int id = 1;
        when(petService.getPetById(id)).thenReturn(Optional.of(pet));

        PetDto returnedPet = petService.getPetById(id);

        verify(petService, times(1)).getPetById(id);
    }

    @Test
    void findByOwnerId() {
    }

    @Test
    void createPet() {
    }

    @Test
    void deletePet() {
    }

    @Test
    void findWithLockingById() {
    }
}