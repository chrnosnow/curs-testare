# learn-testing

[![CI - Run Tests](https://github.com/chrnosnow/curs-testare/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/chrnosnow/curs-testare/actions/workflows/ci.yml)

## Descriere

Proiect didactic pentru cursul de **Testare software**. Conține o clasă utilitară
`ArraySearch` care caută o cheie într-un vector de exact 5 elemente întregi
(`findIndex`) și o suită de teste JUnit 5 organizată pe tehnici de testare:

- **Equivalence Partitioning** — clase de echivalență pentru argumentele `v` și `key`.
- **Boundary Value Analysis** — valori la limită (prima/ultima poziție, capete `Integer.MIN_VALUE` / `Integer.MAX_VALUE`).
- **Statement Coverage** — teste care acoperă toate instrucțiunile metodei.

În plus, proiectul este configurat cu:

- **JaCoCo** — raport de coverage HTML și prag minim impus (80% line + 80% branch).
- **PIT (Pitest)** — mutation testing pentru evaluarea calității testelor.
- **GitHub Actions** — pipeline CI care rulează automat la fiecare push.

## Stack tehnic

- Java 21 (Temurin)
- Maven
- JUnit Jupiter 5.11.4
- JaCoCo 0.8.12
- PIT 1.17.3 (cu `pitest-junit5-plugin`)

## Rulare locală a testelor

Cerințe: **JDK 21** și **Maven 3.9+** instalate (`java -version`, `mvn -v`).

```bash
# instalare dependențe (offline-friendly)
mvn dependency:go-offline

# rulare doar a testelor unitare
mvn test

# rulare teste + verificare prag coverage 80% (echivalent cu pipeline-ul CI)
mvn verify
```

După `mvn verify` raportul de coverage HTML se generează în:

```
target/site/jacoco/index.html
```

### Mutation testing (opțional, durează mai mult)

```bash
mvn test org.pitest:pitest-maven:mutationCoverage
```

Raportul HTML rezultă în `target/pit-reports/<timestamp>/index.html`.

## Pipeline CI

Workflow-ul este definit în [`.github/workflows/ci.yml`](.github/workflows/ci.yml)
și rulează la fiecare `push`. Pași principali:

1. Checkout + setup JDK 21 (cu cache Maven).
2. Instalare dependențe (`mvn dependency:go-offline`).
3. Rulare teste + verificare coverage (`mvn verify`) — pipeline-ul **eșuează** dacă oricare test pică sau dacă coverage-ul scade sub **80%**.
4. Publicare ca artefacte descărcabile a:
   - rapoartelor Surefire (`surefire-reports`)
   - raportului HTML JaCoCo (`jacoco-coverage-report`)
5. Job separat de mutation testing (`continue-on-error: true`) care publică `pit-mutation-report`.
6. Job de notificare la eșec (`notify-failure`, rulează doar la `failure()`) care deschide automat un Issue în repo cu label-ul `ci-failure`, link către run-ul căzut, branch, commit și `@mention` la autorul commit-ului. GitHub trimite email și notificare în clopoțel către watcher-ii repo-ului.

### Semnificația badge-ului

Badge-ul din partea de sus a README-ului reflectă rezultatul ultimei rulări a workflow-ului `CI - Run Tests` pe branch-ul `main`:

| Stare badge | Semnificație |
| --- | --- |
| **`passing`** (verde) | Ultimul push a trecut cu succes: toate testele rulează, coverage-ul este `>= 80%` și build-ul este sănătos. |
| **`failing`** (roșu) | Ultima rulare a eșuat: cel puțin un test a picat **sau** coverage-ul a scăzut sub pragul de 80% **sau** build-ul Maven nu a putut fi finalizat. |
| **`no status`** / gri | Workflow-ul nu a rulat încă pe branch-ul `main` (de exemplu imediat după primul push înainte de finalizarea CI-ului). |

Click pe badge deschide pagina **Actions** unde poți inspecta log-urile și descărca artefactele (rapoarte de coverage și mutation testing).
