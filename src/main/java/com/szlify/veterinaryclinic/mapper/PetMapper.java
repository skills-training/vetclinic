package com.szlify.veterinaryclinic.mapper;

import com.szlify.veterinaryclinic.model.Pet;
import com.szlify.veterinaryclinic.model.PetCommand;
import com.szlify.veterinaryclinic.model.PetDto;
import org.mapstruct.Mapper;

@Mapper
public interface PetMapper {

    PetDto toDto(Pet pet);

    Pet toEntity(PetDto petDto);

    Pet fromCommand(PetCommand petCommand);
}
