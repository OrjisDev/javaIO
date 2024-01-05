package com.ipi.javaio.service;

import com.ipi.javaio.io.SalarieAideADomicileImportCsvService;
import com.ipi.javaio.model.SalarieAideADomicile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Ajoute des données de test si vide au démarrage
 */
@Component
public class DataInitService implements CommandLineRunner {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;
    @Autowired
    private SalarieAideADomicileImportCsvService salarieAideADomicileImportCsvService;

    @Override
    public void run(String... argv) throws Exception {
        if (this.salarieAideADomicileService.countSalaries() != 0) {
            return;
        }

        SalarieAideADomicile s1 = this.salarieAideADomicileService.creerSalarieAideADomicile(
                new SalarieAideADomicile("Jean", LocalDate.parse("2022-09-05"), LocalDate.parse("2022-09-05"),
                        20, 0,
                        80, 10, 1));
        // 2022
        this.salarieAideADomicileService.clotureMois(s1, 6);
        this.salarieAideADomicileService.clotureMois(s1, 6);
        this.salarieAideADomicileService.clotureMois(s1, 5);
        // 2023
        this.salarieAideADomicileService.clotureMois(s1, 5);
        this.salarieAideADomicileService.clotureMois(s1, 8);
        this.salarieAideADomicileService.clotureMois(s1, 3);

        SalarieAideADomicile s2 = this.salarieAideADomicileService.creerSalarieAideADomicile(
                new SalarieAideADomicile("Jeannette", LocalDate.parse("2002-03-20"), LocalDate.parse("2022-09-05"),
                        180, 27.5,
                        200, 30, 20));
        // 2022
        this.salarieAideADomicileService.clotureMois(s2, 11);
        this.salarieAideADomicileService.clotureMois(s2, 10);
        this.salarieAideADomicileService.clotureMois(s2, 7);
        // 2023
        this.salarieAideADomicileService.clotureMois(s2, 10);
        this.salarieAideADomicileService.clotureMois(s2, 11);
        this.salarieAideADomicileService.clotureMois(s2, 8);

        // [TD]
        salarieAideADomicileImportCsvService.importFile("salaries_a_importer.csv");
    }
}
