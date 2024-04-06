package com.szlify.veterinaryclinic.service;

import com.szlify.veterinaryclinic.mapper.VetMapper;
import com.szlify.veterinaryclinic.model.Vet;
import com.szlify.veterinaryclinic.model.VetCommand;
import com.szlify.veterinaryclinic.model.VetDto;
import com.szlify.veterinaryclinic.repository.VetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;
    private final VetMapper vetMapper;


    public List<VetDto> getAllVets(Boolean withVisits) {
        return withVisits != null && withVisits ?
                vetRepository.findAllByVisitsIsNotEmpty().stream()
                        .map(vetMapper::toDto)
                        .toList() :
                vetRepository.findAll().stream()
                        .map(vetMapper::toDto)
                        .toList();
    }

    public VetDto getVetById(int id) {
        return vetMapper.toDto(vetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vet doesnt found")));
    }

    public VetDto createVet(VetCommand vetCommand) {
        Vet vet = vetMapper.fromCommand(vetCommand);
        return vetMapper.toDto(vetRepository.save(vet));
    }

    @Transactional
    @Modifying
    public VetDto updateVet(int vetId, VetCommand vetCommand) {
        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new EntityNotFoundException("Vet doesnt found"));
        if (vet.getFirstName() != null) {
            vet.setFirstName(vetCommand.getFirstName());
        } else if (vet.getLastName() != null) {
            vet.setLastName(vetCommand.getLastName());
        }
        return vetMapper.toDto(vet);
    }

//    public List<VisitDto> getVetVisits(int vetId) {
//        Vet vet = vetRepository.findById(vetId)
//                .orElseThrow(() -> new EntityNotFoundException("Vet not found with ID: " + vetId));
//
//        return visitRepository.findAllByVet(vet).stream()
//                .map(visitMapper::toDto)
//                .toList();
//    }

    @Transactional
    public void deleteVetById(int id) {
        vetRepository.deleteById(id);
    }

    public Vet findWithLockingById(int id) {
        return vetRepository.findWithLockingById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vet not found"));
    }
}
