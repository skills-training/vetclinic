package com.szlify.veterinaryclinic.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VetDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
