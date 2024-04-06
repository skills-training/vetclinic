package com.szlify.veterinaryclinic.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PetCommand {

    @NotBlank(message = "Name can not be null")
    @Pattern(regexp = "[A-ZŁŻ][a-ząćęłńóśźż]+", message = "Invalid name format. The name should start with an uppercase letter followed by lowercase letters.")
    private String name;

    @NotBlank(message = "Species can not be null")
    @Pattern(regexp = "[A-ZŁŻ][a-ząćęłńóśźż]+", message = "Invalid species format. The species should start with an uppercase letter followed by lowercase letters.")
    private String species;

    @Min(value = 1, message = "Value cannot be less than 1!")
    private int ownerId;
}
