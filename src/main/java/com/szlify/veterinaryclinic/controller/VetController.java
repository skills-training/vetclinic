package com.szlify.veterinaryclinic.controller;


import com.szlify.veterinaryclinic.model.VetCommand;
import com.szlify.veterinaryclinic.model.VetDto;
import com.szlify.veterinaryclinic.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vets")
public class VetController {

    private final VetService vetService;

    @GetMapping
    public ResponseEntity<List<VetDto>> getAllVets(@RequestParam(required = false) Boolean withVisits) {
        return new ResponseEntity<>(vetService.getAllVets(withVisits), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VetDto> getVetById(@PathVariable int id) {
        return new ResponseEntity<>(vetService.getVetById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VetDto> createVet(@RequestBody VetCommand vetCommand) {
        return new ResponseEntity<>(vetService.createVet(vetCommand), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VetDto> updateVet(@PathVariable int id, @RequestBody VetCommand vetCommand) {
        return new ResponseEntity<>(vetService.updateVet(id, vetCommand), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVet(@PathVariable int id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
