package at.fichtinger.vorsorgekasse.repository;

import at.fichtinger.vorsorgekasse.entity.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MitarbeiterRepository  extends JpaRepository<Mitarbeiter, Long> {
}
