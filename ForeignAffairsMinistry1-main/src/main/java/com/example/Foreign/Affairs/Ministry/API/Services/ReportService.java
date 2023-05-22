package com.example.Foreign.Affairs.Ministry.API.Services;

import com.example.Foreign.Affairs.Ministry.API.DTO.ReportDTO;
import com.example.Foreign.Affairs.Ministry.API.Modell.ReportTable;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.ReportRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public static final String pathToReports ="C:\\Users\\Amaal\\OneDrive\\Desktop\\Report";

    @Autowired
    ReportRepository reportRepository;
    public String generateReportForTotalRequestAndError() throws FileNotFoundException, JRException {
        List<ReportTable> allUsageStatus = reportRepository.getAllUsageStatus();
        List<ReportDTO> usageReportDTOS = new ArrayList<>();
        for (ReportTable statusTable:allUsageStatus) {
            statusTable.getEndpoint();
            statusTable.getTotalRequests();
            statusTable.getTotalErrors();
            statusTable.getAverageResponseTime();
            ReportDTO usagereportDTO=new ReportDTO(statusTable.getEndpoint(), statusTable.getTotalRequests(),statusTable.getTotalErrors(),statusTable.getAverageResponseTime());
            usageReportDTOS.add(usagereportDTO);
        }
        File file = new File("C:\\Users\\Amaal\\Downloads\\ForeignAffairsMinistry1-main\\ForeignAffairsMinistry1-main\\src\\main\\resources\\Report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(usageReportDTOS);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("CreatedBy", "Amaal");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, pathToReports + "\\Report.pdf");
        return "Report generated : " + pathToReports + "\\Report.pdf";
    }
}
