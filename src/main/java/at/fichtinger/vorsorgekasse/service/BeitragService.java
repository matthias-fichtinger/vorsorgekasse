package at.fichtinger.vorsorgekasse.service;

import at.fichtinger.vorsorgekasse.dto.BeitragResponse;
import at.fichtinger.vorsorgekasse.dto.KontostandResponse;
import at.fichtinger.vorsorgekasse.entity.Beitrag;
import at.fichtinger.vorsorgekasse.entity.Mitarbeiter;
import at.fichtinger.vorsorgekasse.repository.BeitragRepository;
import at.fichtinger.vorsorgekasse.repository.MitarbeiterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class BeitragService {
    private static final BigDecimal BEITRAGSSATZ = new BigDecimal("0.0153");

    private final BeitragRepository beitragRepo;
    private final MitarbeiterRepository mitarbeiterRepo;

    public BeitragService(BeitragRepository beitragRepo, MitarbeiterRepository mitarbeiterRepo) {
        this.beitragRepo = beitragRepo;
        this.mitarbeiterRepo = mitarbeiterRepo;
    }

    public BeitragResponse buchen(Long mitarbeiterId, YearMonth monat){
        Mitarbeiter m = mitarbeiterRepo.findById(mitarbeiterId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mitarbeiter nicht gefunden"));
        LocalDate ersterDesMonats = monat.atDay(1);
        if (beitragRepo.existsByMitarbeiterIdAndMonat(mitarbeiterId, ersterDesMonats)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Beitrag für diesen Monat existiert bereits");
        }

        Beitrag b = new Beitrag();
        b.setMitarbeiter(m);
        b.setMonat(ersterDesMonats);
        b.setBetrag(m.getBruttoGehalt().multiply(BEITRAGSSATZ).setScale(2, RoundingMode.HALF_UP));
        Beitrag gespeichert = beitragRepo.save(b);
        return new BeitragResponse(gespeichert.getId(), mitarbeiterId,
                gespeichert.getMonat(), gespeichert.getBetrag());
    }

    public KontostandResponse kontostand(Long mitarbeiterId) {
        BigDecimal summe = beitragRepo.findByMitarbeiterId(mitarbeiterId).stream()
                .map(Beitrag::getBetrag)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new KontostandResponse(mitarbeiterId, summe);
    }
}

