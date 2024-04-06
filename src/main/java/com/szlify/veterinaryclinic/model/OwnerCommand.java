package com.szlify.veterinaryclinic.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OwnerCommand {

    @NotBlank(message = "First Name can not be null")
    @Pattern(regexp = "[A-ZŁŻ][a-ząćęłńóśźż]+", message = "Invalid first name format. The name should start with an uppercase letter followed by lowercase letters.")
    private String firstName;

    @NotBlank(message = "Last Name can not be null")
    @Pattern(regexp = "[A-ZŁŻ][a-ząćęłńóśźż]+", message = "Invalid last name format. The name should start with an uppercase letter followed by lowercase letters.")
    private String lastName;

    @NotNull(message = "Email is required")
    @Email
    private String Email;

}
