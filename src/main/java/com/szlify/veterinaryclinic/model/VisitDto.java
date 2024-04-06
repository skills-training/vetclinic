package com.szlify.veterinaryclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VisitDto {

    private int id;
    private LocalDateTime dateTime;
    private int vetId;
    private int petId;
}
