package at.fichtinger.vorsorgekasse.dto;

import at.fichtinger.vorsorgekasse.entity.Mitarbeiter;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BeitragResponse(Long id, Long mitarbeiterId, LocalDate monat, BigDecimal betrag) {
}
