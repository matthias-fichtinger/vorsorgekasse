package at.fichtinger.vorsorgekasse.repository;

import at.fichtinger.vorsorgekasse.entity.Beitrag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BeitragRepository extends JpaRepository<Beitrag,Long> {
    List<Beitrag> findByMitarbeiterId(Long mitarbeiterId);
    boolean existsByMitarbeiterIdAndMonat(Long mitarbeiterId, LocalDate monat);
}
