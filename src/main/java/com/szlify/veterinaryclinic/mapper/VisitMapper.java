package com.szlify.veterinaryclinic.mapper;

import com.szlify.veterinaryclinic.model.Visit;
import com.szlify.veterinaryclinic.model.VisitCommand;
import com.szlify.veterinaryclinic.model.VisitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VisitMapper {

    @Mapping(source = "vet.id", target = "vetId")
    @Mapping(source = "pet.id", target = "petId")
    VisitDto toDto(Visit visit);

    Visit toEntity(VisitDto visitDto);


    Visit fromCommand(VisitCommand visitCommand);
}
