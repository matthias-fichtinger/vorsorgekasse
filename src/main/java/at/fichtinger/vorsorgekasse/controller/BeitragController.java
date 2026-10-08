package at.fichtinger.vorsorgekasse.controller;

import at.fichtinger.vorsorgekasse.dto.BeitragResponse;
import at.fichtinger.vorsorgekasse.dto.KontostandResponse;
import at.fichtinger.vorsorgekasse.entity.Beitrag;
import at.fichtinger.vorsorgekasse.service.BeitragService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/mitarbeiter/{id}")
public class BeitragController {
    private final BeitragService service;

    public BeitragController(BeitragService service) {
        this.service = service;
    }

    @PostMapping("beitraege")
    public BeitragResponse anlegen(@PathVariable Long id, @RequestParam YearMonth monat){
        return  service.buchen(id,monat);
    }

    @GetMapping("/kontostand")
    public KontostandResponse kontostand(@PathVariable Long id){
        return service.kontostand(id);
    }
}
