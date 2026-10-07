package at.fichtinger.vorsorgekasse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Beitrag {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Mitarbeiter mitarbeiter;

    private LocalDate monat;
    private BigDecimal betrag;

    public Beitrag() {
    }

    public Long getId() {
        return id;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public LocalDate getMonat() {
        return monat;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public void setMonat(LocalDate monat) {
        this.monat = monat;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }
}
