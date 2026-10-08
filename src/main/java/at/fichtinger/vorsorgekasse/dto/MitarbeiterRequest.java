package at.fichtinger.vorsorgekasse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record MitarbeiterRequest (
        @NotBlank(message = "Name ist erforderlich") String name,
        @NotNull(message = "Bruttogehalt ist erforderlich")
        @Positive(message = "Bruttogehalt muss positiv sein")BigDecimal bruttoGehalt
        ){

}
