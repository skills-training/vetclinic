package com.szlify.veterinaryclinic.controller;

import com.szlify.veterinaryclinic.model.VisitCommand;
import com.szlify.veterinaryclinic.model.VisitDto;
import com.szlify.veterinaryclinic.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/visits")
public class VisitController {

    private final VisitService visitService;

    @GetMapping
    public ResponseEntity<Page<VisitDto>> getAllVisit(@PageableDefault(size = 20) Pageable pageable) {
        return new ResponseEntity<>(visitService.getAllVisits(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitDto> getVisitById(@PathVariable int id) {
        return new ResponseEntity<>(visitService.getVisitById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VisitDto> createDto(@RequestBody VisitCommand visitCommand) {
        return new ResponseEntity<>(visitService.createVisit(visitCommand), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/update")
    public ResponseEntity<VisitDto> updateDto(@PathVariable int id, @RequestBody VisitCommand visitCommand) {
        return new ResponseEntity<>(visitService.updateVisit(id, visitCommand), HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('VET')")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        visitService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
