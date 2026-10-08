package at.fichtinger.vorsorgekasse.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class BeitragGebuchtListener {

    private static final Logger log = LoggerFactory.getLogger(BeitragGebuchtListener.class);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onBeitragGebucht(BeitragGebuchtEvent event) {
        log.info("Benachrichtigung: Beitrag von {} € für Mitarbeiter {} im Monat {} gebucht (Event {})",
                event.betrag(), event.mitarbeiterId(), event.monat(), event.eventId());
    }
}