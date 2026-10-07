package at.fichtinger.vorsorgekasse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
public class Mitarbeiter {
    @Id @GeneratedValue
    private long id;
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private BigDecimal bruttoGehalt;

    public Mitarbeiter() {
    }

    public void setBruttoGehalt(BigDecimal bruttoGehalt) {
        this.bruttoGehalt = bruttoGehalt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBruttoGehalt() {
        return bruttoGehalt;
    }
}
