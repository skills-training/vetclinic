package com.szlify.veterinaryclinic.mapper;

import com.szlify.veterinaryclinic.model.Owner;
import com.szlify.veterinaryclinic.model.OwnerCommand;
import com.szlify.veterinaryclinic.model.OwnerDto;
import org.mapstruct.Mapper;

@Mapper
public interface OwnerMapper {

    OwnerDto toDto(Owner owner);

    Owner toEntity(OwnerDto ownerDto);

    Owner fromCommand(OwnerCommand ownerCommand);
}
