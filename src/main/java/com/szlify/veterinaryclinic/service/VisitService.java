package com.szlify.veterinaryclinic.service;

import com.szlify.veterinaryclinic.mapper.VisitMapper;
import com.szlify.veterinaryclinic.model.*;
import com.szlify.veterinaryclinic.repository.PetRepository;
import com.szlify.veterinaryclinic.repository.VetRepository;
import com.szlify.veterinaryclinic.repository.VisitRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;
    private final PetRepository petRepository;
    private final VetRepository vetRepository;
    private final RabbitMqService rabbitMQService;


    public Page<VisitDto> getAllVisits(Pageable pageable) {
        Page<Visit> visitPage = visitRepository.findAll(pageable);

        return visitPage.map(visitMapper::toDto);
    }

    public VisitDto getVisitById(int id) {
        return visitMapper.toDto(visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visit doesnt found")));
    }

    @Transactional
    public VisitDto createVisit(VisitCommand visitCommand) {
        LocalDateTime localDateTime = visitCommand.getDateTime();
        System.out.println("Received DateTime: " + localDateTime);

        int petId = visitCommand.getPetId();
        int vetId = visitCommand.getVetId();

        Pet pet = petRepository.findWithLockingById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        Vet vet = vetRepository.findWithLockingById(vetId)
                .orElseThrow(() -> new EntityNotFoundException("Vet not found"));


        if (localDateTime != null && visitRepository.existsAllByVetIdAndAndDateTime(vetId, localDateTime.plusHours(1L))) {
            throw new EntityExistsException("Visit already exists!");
        }

        Visit visit = visitMapper.fromCommand(visitCommand);
        visit.setPet(pet);
        visit.setVet(vet);
        visit.setDateTime(localDateTime);
        rabbitMQService.sendMessage(visit);
        return visitMapper.toDto(visitRepository.save(visit));
    }

    @Transactional
    @Modifying
    public VisitDto updateVisit(int visitId, VisitCommand visitCommand) {
        LocalDateTime newDateTime = visitCommand.getDateTime();
        int newPetId = visitCommand.getPetId();
        int newVetId = visitCommand.getVetId();

        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new EntityNotFoundException("Visit not found"));

        Pet newPet = petRepository.findWithLockingById(newPetId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));

        Vet newVet = vetRepository.findWithLockingById(newVetId)
                .orElseThrow(() -> new EntityNotFoundException("Vet not found"));

        if (newDateTime != null && visitRepository.existsAllByVetIdAndAndDateTime(newVetId, newDateTime) ||
                visitRepository.existsAllByVetIdAndAndDateTime(newVetId, newDateTime.plusHours(1L))) {
            throw new EntityExistsException("Visit already exists for the given time!");
        }


        visit.setDateTime(newDateTime);
        visit.setPet(newPet);
        visit.setVet(newVet);

        return visitMapper.toDto(visitRepository.save(visit));
    }

    @Transactional
    public void deleteById(int id) {
        visitRepository.deleteById(id);
    }
}
