package com.realdolmen.EuropeanHub.report;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.realdolmen.EuropeanHub.common.NotFoundException;
import com.realdolmen.EuropeanHub.profile.ProfileEU;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @PostMapping("/reports")
    Report newReport(@RequestBody Report newReport) throws MessagingException, DocumentException, BadElementException, IOException {
       PdfWriterManager pdfWriterManager = new PdfWriterManager(newReport);
       String pdfReportString = pdfWriterManager.generatePDF();
       newReport.setPdfReport(pdfReportString);
        /*
        try{
            notificationService.sendNotification(newReport);
        }catch(MailException e){
            // catch error
            System.out.println("Error sending mail: " + e.getMessage());
        }*/
        for (ProfileEU profile : newReport.getProfiles()) {

            emailServiceImpl.sendMessageWithAttachment(profile.getEmail(),
                    "Jouw aanrijdingsformulier",
                    String.format("Beste %s, %n%nIn bijlage kan je jouw aanrijdingsformulier van %s vinden. %n%nMet vriendelijke groeten,%nHet European Hub Team",
                            profile.getFirstName(), newReport.getDateCrash().toString()),
                    pdfReportString);
        }
        return reportRepository.save(newReport);
    }

    @GetMapping("/reports/{id}")
    Report one(@PathVariable int id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/reports/{id}")
    Report replaceReport(@RequestBody Report newReport, @PathVariable int id) {

        return reportRepository.findById(id)
                .map(report -> {
                    report.setProfiles(newReport.getProfiles());
                    return reportRepository.save(report);
                })
                .orElseGet(() -> {
                    newReport.setId(id);
                    return reportRepository.save(newReport);
                });
    }

    @DeleteMapping("/reports/{id}")
    void deleteReport(@PathVariable int id) {
        reportRepository.deleteById(id);
    }

}
