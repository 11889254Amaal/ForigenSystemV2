package com.example.Foreign.Affairs.Ministry.API.Controller;

import com.example.Foreign.Affairs.Ministry.API.Services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(value = "Report")
public class ReportController {

    @Autowired
    public
    ReportService reportService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/MyReport", method = RequestMethod.GET)
    public String generateReport() throws FileNotFoundException, JRException {

        return reportService.generateReportForTotalRequestAndError();
    }
}
