package at.fichtinger.vorsorgekasse.service;

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

    public Mitarbeiter anlegen(Mitarbeiter mitarbeiter){
        return repo.save(mitarbeiter);
    }

    public List<Mitarbeiter> alle() {return repo.findAll();}
}
