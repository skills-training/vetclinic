package com.szlify.veterinaryclinic.model;

import jakarta.validation.constraints.Email;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OwnerDto {

    private int id;
    private String firstName;
    private String lastName;

    @Email
    private String email;
}
