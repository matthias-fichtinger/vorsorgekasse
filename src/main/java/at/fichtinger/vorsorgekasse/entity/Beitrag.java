package at.fichtinger.vorsorgekasse.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(
        name = "uk_beitrag_mitarbeiter_monat",
        columnNames = {"mitarbeiter_id", "monat"}))
public class Beitrag {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mitarbeiter_id", nullable = false)
    private Mitarbeiter mitarbeiter;

    @Column(nullable = false)
    private LocalDate monat;

    @Column(nullable = false, precision = 12, scale = 2)
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
