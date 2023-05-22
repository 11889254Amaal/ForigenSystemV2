package com.example.Foreign.Affairs.Ministry.API.Controller;

import com.example.Foreign.Affairs.Ministry.API.Controller.ReportController;
import com.example.Foreign.Affairs.Ministry.API.Services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.access.AccessDeniedException;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ReportControllerTest {
    private ReportController reportController;

    @Mock
    private ReportService reportServiceMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reportController = new ReportController();
        reportController.reportService = reportServiceMock;
    }

    @Test
    public void testGenerateReport_Success() throws FileNotFoundException, JRException {
        // Arrange
        when(reportServiceMock.generateReportForTotalRequestAndError()).thenReturn("Report generated");

        // Act
        String result = reportController.generateReport();

        // Assert
        assertEquals("Report generated", result);

        // Verify that the generateReportForTotalRequestAndError method was called
        verify(reportServiceMock).generateReportForTotalRequestAndError();
    }

    @Test
    public void testGenerateReport_AccessDenied() throws FileNotFoundException, JRException {
        // Arrange
        doThrow(AccessDeniedException.class).when(reportServiceMock).generateReportForTotalRequestAndError();

        // Act and Assert
        assertThrows(AccessDeniedException.class, () -> reportController.generateReport());

        // Verify that the generateReportForTotalRequestAndError method was called
        verify(reportServiceMock).generateReportForTotalRequestAndError();
    }
}
