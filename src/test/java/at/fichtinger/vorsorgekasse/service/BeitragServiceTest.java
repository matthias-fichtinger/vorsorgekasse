package at.fichtinger.vorsorgekasse.service;

import at.fichtinger.vorsorgekasse.dto.BeitragResponse;
import at.fichtinger.vorsorgekasse.dto.KontostandResponse;
import at.fichtinger.vorsorgekasse.entity.Beitrag;
import at.fichtinger.vorsorgekasse.entity.Mitarbeiter;
import at.fichtinger.vorsorgekasse.repository.BeitragRepository;
import at.fichtinger.vorsorgekasse.repository.MitarbeiterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeitragServiceTest {
    @Mock
    BeitragRepository beitragRepository;
    @Mock
    MitarbeiterRepository mitarbeiterRepository;
    @InjectMocks
    BeitragService beitragService;

    @Test
    void buchen_berechnet153ProzentVomBrutto(){
        Mitarbeiter m = new Mitarbeiter();
        m.setBruttoGehalt(new BigDecimal("3500.00"));
        when(mitarbeiterRepository.findById(1L)).thenReturn(Optional.of(m));
        when(beitragRepository.save(any(Beitrag.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BeitragResponse b = beitragService.buchen(1L, YearMonth.of(2026,9));

        assertEquals(new BigDecimal("53.55"), b.betrag());
    }

    @Test
    void buchen_beitragSchonGebucht(){
        Mitarbeiter m = new Mitarbeiter();
        m.setBruttoGehalt(new BigDecimal("3500.00"));
        m.setName("Mattej");
        when(mitarbeiterRepository.findById(1L)).thenReturn(Optional.of(m));
        when(beitragRepository.existsByMitarbeiterIdAndMonat(any(),any())).thenReturn(true);
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> beitragService.buchen(1L,YearMonth.of(2026,9)));
        assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
        verify(beitragRepository, never()).save(any());
    }

    @Test
    void buchen_mitarbeiterExistiertNicht(){
        when(mitarbeiterRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> beitragService.buchen(1L, YearMonth.of(2026,9)));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        verify(beitragRepository, never()).existsByMitarbeiterIdAndMonat(any(),any());
        verify(beitragRepository,never()).save(any());
    }

    @Test
    void kontostand_mehrereBetraegeSummiert(){
        Beitrag b1 = new Beitrag();
        Beitrag b2 = new Beitrag();
        b1.setBetrag(new BigDecimal("53.55"));
        b2.setBetrag(new BigDecimal("53.55"));
        when(beitragRepository.findByMitarbeiterId(any())).thenReturn(List.of(b1,b2));
        KontostandResponse kontostand = beitragService.kontostand(1L);
        assertEquals(new BigDecimal("107.10"), kontostand.kontostand());
    }

    @Test
    void kontostand_keineBeitraege(){
        when(beitragRepository.findByMitarbeiterId(1L)).thenReturn(List.of());
        KontostandResponse kontostand = beitragService.kontostand(1L);
        assertEquals(BigDecimal.ZERO, kontostand.kontostand());
    }
}