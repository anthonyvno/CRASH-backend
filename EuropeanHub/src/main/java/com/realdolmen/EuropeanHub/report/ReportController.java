package com.realdolmen.EuropeanHub.report;

import common.NotFoundException;
import java.util.List;
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

    ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/reports")
    List<Report> all() {
        return reportRepository.findAll();
    }

    @PostMapping("/reports")
    Report newReport(@RequestBody Report newReport) {
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
