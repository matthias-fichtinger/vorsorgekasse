package at.fichtinger.vorsorgekasse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Mitarbeiter {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal bruttoGehalt;

    public Mitarbeiter() {
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getBruttoGehalt() { return bruttoGehalt; }

    public void setName(String name) { this.name = name; }
    public void setBruttoGehalt(BigDecimal bruttoGehalt) { this.bruttoGehalt = bruttoGehalt; }
}