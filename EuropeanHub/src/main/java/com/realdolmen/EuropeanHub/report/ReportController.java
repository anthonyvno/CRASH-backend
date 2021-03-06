package com.realdolmen.EuropeanHub.report;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.realdolmen.EuropeanHub.common.NotFoundException;
import com.realdolmen.EuropeanHub.profile.ProfileEU;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ReportController {

    private final ReportRepository reportRepository;

    @Autowired
    private final EmailServiceImpl emailServiceImpl;

    //private final NotificationService notificationService;
    ReportController(ReportRepository reportRepository, EmailServiceImpl emailServiceImpl/*, NotificationService notificationService*/) {
        this.reportRepository = reportRepository;
        this.emailServiceImpl = emailServiceImpl;

        //  this.notificationService = notificationService;
    }

    @GetMapping("/reports")
    List<Report> all() {
        return reportRepository.findAll();
    }

    @GetMapping("/reports/insurer/{insurerName}")
    List<Report> byInsurerName(@PathVariable String insurerName) {
        return reportRepository.findByInsurerName(insurerName);
    }

    @PostMapping("/reports")
    Report newReport(@RequestBody Report newReport) throws MessagingException, ConnectException, DocumentException, BadElementException, IOException {
        PdfWriterManager pdfWriterManager = new PdfWriterManager(newReport);
        String pdfReportString = pdfWriterManager.generatePDF();
        byte[] bytes
                = null;

        bytes
                = Files
                        .readAllBytes(Paths
                                .get(pdfReportString));
        newReport
                .setPdfReport(Base64
                        .getEncoder().encodeToString(bytes
                        ));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        String strDate = formatter.format(newReport.getDateCrash());

        try {
            for (ProfileEU profile : newReport.getProfiles()) {
                emailServiceImpl.sendMessageWithAttachment(profile.getEmail(),
                        "Jouw aanrijdingsformulier",
                        String.format("Beste %s, %n%nIn bijlage kan je jouw aanrijdingsformulier van %s vinden. %n%nMet vriendelijke groeten,%nHet CRASH Team",
                                profile.getFirstName(), strDate),
                        pdfReportString, newReport.getPictures());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Volledig postmethode doorlopen");
        System.out.println(newReport.toString());
        return reportRepository.save(newReport);
    }

    @GetMapping("/reports/{id}")
    Report
            one(@PathVariable int id
            ) {
        return reportRepository
                .findById(id
                )
                .orElseThrow(() -> new NotFoundException(id
        ));

    }

    @PutMapping("/reports/{id}")
    Report
            replaceReport(@RequestBody Report newReport,
                    @PathVariable int id
            ) {

        return reportRepository
                .findById(id
                )
                .map(report
                        -> {
                    report
                            .setProfiles(newReport
                                    .getProfiles());

                    return reportRepository
                            .save(report
                            );

                })
                .orElseGet(() -> {
                    newReport
                            .setId(id
                            );

                    return reportRepository
                            .save(newReport
                            );

                });

    }

    @DeleteMapping("/reports/{id}")

    void deleteReport(@PathVariable int id
    ) {
        reportRepository
                .deleteById(id
                );

    }

    @PostMapping(path = "/reports/pdf")
    Report
            createPdf(@RequestBody Report newReport
            ) throws IOException,
            FileNotFoundException {

        byte[] bytes
                = null;

        try {
            bytes
                    = Files
                            .readAllBytes(Paths
                                    .get(new PdfWriterManager(newReport
                                    ).generatePDF()));

        } catch (DocumentException ex) {
            Logger
                    .getLogger(ReportController.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        newReport
                .setPdfReport(Base64
                        .getEncoder().encodeToString(bytes
                        ));

        return newReport;

    }

    @PostMapping(value = "/reports/downloadexcel")
    public ResponseEntity<InputStreamResource> excelCustomersReport(@RequestBody int[] idArray) throws IOException {
        //Report report = reportRepository.getOne(id);
        List<Report> reports = new ArrayList<>();
        for(int id : idArray){
            reports.add(reportRepository.getOne(id));
        }
        
        ByteArrayInputStream in = new GenerateExcelReport().reportToExcel(reports);
        // return IO ByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        // set filename in header
        headers.add("Content-Disposition", "attachment; filename=report.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

}
