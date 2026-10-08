package at.fichtinger.vorsorgekasse.dto;

import java.math.BigDecimal;

public record MitarbeiterResponse(
        Long id, String name, BigDecimal bruttoGehalt
) {
}
