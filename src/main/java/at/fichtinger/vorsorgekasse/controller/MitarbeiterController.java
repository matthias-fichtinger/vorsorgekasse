package at.fichtinger.vorsorgekasse.controller;

import at.fichtinger.vorsorgekasse.dto.MitarbeiterRequest;
import at.fichtinger.vorsorgekasse.dto.MitarbeiterResponse;
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
    public MitarbeiterResponse anlegen(@Valid @RequestBody MitarbeiterRequest request){
        return service.anlegen(request);
    }

    @GetMapping
    public List<MitarbeiterResponse> alle(){
        return service.alle();
    }
}
