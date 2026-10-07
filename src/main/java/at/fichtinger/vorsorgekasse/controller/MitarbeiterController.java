package at.fichtinger.vorsorgekasse.controller;

import at.fichtinger.vorsorgekasse.entity.Mitarbeiter;
import at.fichtinger.vorsorgekasse.service.MitarbeiterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mitarbeiter")
public class MitarbeiterController {
    private final MitarbeiterService service;

    public MitarbeiterController(MitarbeiterService service) {
        this.service = service;
    }

    @PostMapping
    public Mitarbeiter anlegen(@Valid @RequestBody Mitarbeiter m){
        return service.anlegen(m);
    }

    @GetMapping
    public List<Mitarbeiter> alle(){
        return service.alle();
    }
}
