package at.fichtinger.vorsorgekasse.service;

import at.fichtinger.vorsorgekasse.dto.MitarbeiterRequest;
import at.fichtinger.vorsorgekasse.dto.MitarbeiterResponse;
import at.fichtinger.vorsorgekasse.entity.Mitarbeiter;
import at.fichtinger.vorsorgekasse.repository.MitarbeiterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MitarbeiterService {
    private final MitarbeiterRepository repo;

    public MitarbeiterService(MitarbeiterRepository repo) {
        this.repo = repo;
    }

    public MitarbeiterResponse anlegen(MitarbeiterRequest request) {
        Mitarbeiter m = new Mitarbeiter();
        m.setName(request.name());
        m.setBruttoGehalt(request.bruttoGehalt());
        return toResponse(repo.save(m));
    }

    public List<MitarbeiterResponse> alle() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    private MitarbeiterResponse toResponse(Mitarbeiter m) {
        return new MitarbeiterResponse(m.getId(), m.getName(), m.getBruttoGehalt());
    }
}
