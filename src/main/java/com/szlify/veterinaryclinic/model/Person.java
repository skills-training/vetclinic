package com.szlify.veterinaryclinic.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Person {

    private String name;
    private final LocalDate localDate = LocalDate.now();

}