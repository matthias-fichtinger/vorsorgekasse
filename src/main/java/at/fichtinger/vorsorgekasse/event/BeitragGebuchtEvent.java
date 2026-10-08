package at.fichtinger.vorsorgekasse.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record BeitragGebuchtEvent (
    UUID eventId,
    Instant occuredAt,
    Long mitarbeiterId,
    LocalDate monat,
    BigDecimal betrag
    ){
    public static BeitragGebuchtEvent of(Long mitarbeiterId, LocalDate monat, BigDecimal betrag){
        return new BeitragGebuchtEvent(UUID.randomUUID(), Instant.now(), mitarbeiterId,monat,betrag);
    }
}