package at.fichtinger.vorsorgekasse.dto;

import java.math.BigDecimal;

public record KontostandResponse(Long mitarbeiterId, BigDecimal kontostand) {
}
