package com.szlify.veterinaryclinic.controller;

import com.szlify.veterinaryclinic.model.PetCommand;
import com.szlify.veterinaryclinic.model.PetDto;
import com.szlify.veterinaryclinic.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pets")
public class PetController {

    private final PetService petService;

    @GetMapping
    public ResponseEntity<Page<PetDto>> getAllPets(@PageableDefault(size = 20) Pageable pageable) {
        return new ResponseEntity<>(petService.getAllPets(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getPetById(@PathVariable int id) {
        return new ResponseEntity<>(petService.getPetById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody PetCommand petCommand) {
        return new ResponseEntity<>(petService.createPet(petCommand), HttpStatus.CREATED);
    }

    @GetMapping("/byOwner/{ownerId}")
    public ResponseEntity<List<PetDto>> getPetsByOwnerId(@PathVariable int ownerId) {
        return new ResponseEntity<>(petService.findByOwnerId(ownerId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        petService.deletePet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
