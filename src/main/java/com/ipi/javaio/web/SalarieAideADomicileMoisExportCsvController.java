package com.ipi.javaio.web;

import com.ipi.javaio.export.SalarieAideADomicileMoisExportCsvService;
import com.ipi.javaio.export.SalarieAideADomicileExportCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("export")
public class SalarieAideADomicileMoisExportCsvController {

    @Autowired
    private final SalarieAideADomicileMoisExportCsvService salarieAideADomicileMoisExportCsvService;

    @Autowired
    private final SalarieAideADomicileExportCsvService salarieAideADomicileExportCsvService;

    public SalarieAideADomicileMoisExportCsvController(SalarieAideADomicileMoisExportCsvService salarieAideADomicileMoisExportCsvService,
                                                       SalarieAideADomicileExportCsvService salarieAideADomicileExportCsvService) {
        this.salarieAideADomicileMoisExportCsvService = salarieAideADomicileMoisExportCsvService;
        this.salarieAideADomicileExportCsvService = salarieAideADomicileExportCsvService;
    }

    @GetMapping("/salarieAideADomicile/csv")
    public void salarieAideADomicileCsv(HttpServletRequest request,HttpServletResponse response)throws IOException{
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition","attachment; filename=\"export_salarie.csv\"");
        PrintWriter writer = response.getWriter();
        this.salarieAideADomicileExportCsvService.export(writer);
    }

    @GetMapping("/salarieAideADomicileMois/csv")
    public void salarieAideADomicileMoisCsv(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois.csv\"");
        PrintWriter writer = response.getWriter();
        this.salarieAideADomicileMoisExportCsvService.export(writer);
    }

    @GetMapping("/salarieAideADomicileMois/csv/{salarieId}")
    public void salarieAideADomicileMoisCsv(@PathVariable("salarieId") Long salarieId,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois_" + salarieId + ".csv\"");
        PrintWriter writer = response.getWriter();
        this.salarieAideADomicileMoisExportCsvService.export(writer, salarieId);
    }

    @GetMapping("/salarieAideADomicileMois/csv/{salarieId}/{annee}")
    public void salarieAideADomicileMoisCsv(@PathVariable("salarieId") Long salarieId, @PathVariable("annee") int annee,
                                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois" + salarieId + "_" + annee + ".csv\"");
        PrintWriter writer = response.getWriter();
        this.salarieAideADomicileMoisExportCsvService.export(writer, salarieId, annee);
    }

}
