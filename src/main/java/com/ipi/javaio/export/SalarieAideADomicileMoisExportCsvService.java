package com.ipi.javaio.export;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.model.SalarieAideADomicileMois;
import com.ipi.javaio.repository.SalarieAideADomicileMoisRepository;
import com.ipi.javaio.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Service
public class SalarieAideADomicileMoisExportCsvService {

    @Autowired
    private final SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository;
    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public SalarieAideADomicileMoisExportCsvService(
            SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository,
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileMoisRepository = salarieAideADomicileMoisRepository;
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(PrintWriter writer, Long salarieId, int annee) {
        SalarieAideADomicile salarieAideADomicile = salarieAideADomicileService.getSalarie(salarieId);
        //List<SalarieAideADomicileMois> mois = salarieAideADomicileMoisRepository.getMois(salarieAideADomicile, annee);
        List<SalarieAideADomicileMois> allMois = salarieAideADomicileMoisRepository.findBySalarieAideADomicileAndAnnee(salarieAideADomicile, annee);
        exportBase(writer, allMois);
    }
    public void export(PrintWriter writer, Long salarieId) {
        SalarieAideADomicile salarieAideADomicile = salarieAideADomicileService.getSalarie(salarieId);
        List<SalarieAideADomicileMois> allMois = salarieAideADomicileMoisRepository.findBySalarieAideADomicile(salarieAideADomicile);
        exportBase(writer, allMois);
    }
    public void export(PrintWriter writer) {
        Iterable<SalarieAideADomicileMois> allMois = salarieAideADomicileMoisRepository.findAll();
        exportBase(writer, allMois);
    }
    public void exportBase(PrintWriter writer, Iterable<SalarieAideADomicileMois> allMois) {
        for (SalarieAideADomicileMois mois : allMois) {
            SalarieAideADomicile salarie = mois.getSalarieAideADomicile();
            // TODO [TD]
        }
        writer.close();
    }


}
