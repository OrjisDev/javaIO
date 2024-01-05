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
public class SalarieAideADomicileExportCsvService {

    @Autowired
    private final SalarieAideADomicileService SalarieAideADomicileService;

    public SalarieAideADomicileExportCsvService(SalarieAideADomicileService salarieAideADomicileService) {
        this.SalarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(PrintWriter writer) {
        Iterable<SalarieAideADomicile> allSalaries = SalarieAideADomicileService.getSalaries();
        exportBase(writer,allSalaries);
    }

    private void exportBase(PrintWriter writer, Iterable<SalarieAideADomicile> allSalaries) {
        for (SalarieAideADomicile salarie : allSalaries){
            String line = salarie.getNom();
            writer.println(line);
        }
        writer.close();
    }
}
