package com.szlify.veterinaryclinic.mapper;

import com.szlify.veterinaryclinic.model.Vet;
import com.szlify.veterinaryclinic.model.VetCommand;
import com.szlify.veterinaryclinic.model.VetDto;
import org.mapstruct.Mapper;

@Mapper
public interface VetMapper {

    VetDto toDto(Vet vet);

    Vet toEntity(VetDto vetDto);

    Vet fromCommand(VetCommand vetCommand);
}
