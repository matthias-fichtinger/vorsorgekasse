# Vorsorgekasse API

![CI](https://github.com/matthias-fichtinger/vorsorgekasse/actions/workflows/ci.yml/badge.svg)

Vereinfachte REST-API für eine betriebliche Vorsorgekasse (Abfertigung Neu):
Arbeitgeber zahlen monatlich 1,53 % des Bruttogehalts für jeden Mitarbeiter ein.

## Tech-Stack
Java 21 · Spring Boot 4 · Spring Data JPA · PostgreSQL · H2 · Maven ·
JUnit 5 · Mockito · Docker · GitHub Actions · Swagger/OpenAPI

## Funktionen
- Mitarbeiter mit Bruttogehalt anlegen und abrufen
- Monatsbeitrag buchen (1,53 %, kaufmännisch auf 2 Stellen gerundet)
- Schutz gegen doppelte Buchung desselben Monats
- Kontostand pro Mitarbeiter abfragen
- Validierung mit verständlichen Fehlermeldungen (RFC 9457 ProblemDetail)

## Endpoints
| Methode | Pfad | Beschreibung |
|---|---|---|
| POST | `/api/mitarbeiter` | Mitarbeiter anlegen |
| GET | `/api/mitarbeiter` | Alle Mitarbeiter |
| POST | `/api/mitarbeiter/{id}/beitraege?monat=2026-09` | Monatsbeitrag buchen |
| GET | `/api/mitarbeiter/{id}/kontostand` | Kontostand abfragen |

Interaktive Doku: `http://localhost:8080/swagger-ui.html`

## Starten
**Lokal mit H2 (ohne Setup):**
```
./mvnw spring-boot:run
```

**Mit PostgreSQL:**
```
docker compose up -d
```
Dann mit Profil `prod` und den Umgebungsvariablen `DB_URL`, `DB_USER`, `DB_PASSWORD` starten.

**Tests:**
```
./mvnw test
```

## Design-Entscheidungen
- **BigDecimal statt double** für alle Geldbeträge, um Rundungsfehler zu vermeiden
- **Schichtenarchitektur** Controller → Service → Repository mit Constructor Injection
- **DTOs** trennen API-Vertrag und Datenbankmodell
- **Spring-Profile und Umgebungsvariablen**: keine Zugangsdaten im Code, Umstieg auf Cloud-Datenbank ohne Codeänderung
- **Zentrale Fehlerbehandlung** über `@RestControllerAdvice`

## Einsatz von KI
Bei der Entwicklung habe ich Claude als Unterstützung genutzt. Jeder generierte
Codeteil wurde von mir gelesen, nachvollzogen und durch Unit-Tests abgesichert.