package com.szlify.veterinaryclinic.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VisitCommand {

    @NotNull(message = "Date cannot be null")
    @FutureOrPresent(message = "Date has to be planned in the present or future time")
    private LocalDateTime dateTime;

    @Min(value = 1, message = "Value cannot be less than 1!")
    private int vetId;

    @Min(value = 1, message = "Value cannot be less than 1!")
    private int petId;
}
