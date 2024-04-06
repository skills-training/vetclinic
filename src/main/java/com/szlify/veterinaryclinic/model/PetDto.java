package com.szlify.veterinaryclinic.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PetDto {

    private int id;
    private String name;
    private String species;
    private int ownerId;
}
