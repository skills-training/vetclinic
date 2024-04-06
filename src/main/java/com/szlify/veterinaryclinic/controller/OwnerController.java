package com.szlify.veterinaryclinic.controller;

import com.szlify.veterinaryclinic.model.OwnerCommand;
import com.szlify.veterinaryclinic.model.OwnerDto;
import com.szlify.veterinaryclinic.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAllOwners(@RequestParam(name = "withPets", required = false) Boolean withPets) {
        return new ResponseEntity<>(ownerService.getAllOwners(withPets), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable int id) {
        return new ResponseEntity<>(ownerService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OwnerDto> createOwner(@RequestBody @Valid OwnerCommand ownerCommand) {
        return new ResponseEntity<>(ownerService.createOwner(ownerCommand), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        ownerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

