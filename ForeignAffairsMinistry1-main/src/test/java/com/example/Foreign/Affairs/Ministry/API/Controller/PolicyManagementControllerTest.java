package com.example.Foreign.Affairs.Ministry.API.Controller;

import com.example.Foreign.Affairs.Ministry.API.Modell.ReportTable;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.CreatenNewsRequest;
import com.example.Foreign.Affairs.Ministry.API.Services.NewsServices;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolicyManagementControllerTest {

    @Test
    void createCustomer() {
        NewsServices newsServices = new NewsServices();
        CreatenNewsRequest createnNewsRequest = new CreatenNewsRequest();
        ReportTable reportTable = new ReportTable(1,"NewsManagement",6,2,3,4);
        //newsServices.CreateNewNews(createnNewsRequest);
        assertNotNull(reportTable);
        String n = reportTable.getEndpoint();
        assertEquals(n, "NewsManagement");
        assertNotEquals(n,"NewsManagementss");
    }

    @Test
    void updateCustomer() {
        NewsServices newsServices = new NewsServices();
        CreatenNewsRequest createnNewsRequest = new CreatenNewsRequest();
        ReportTable reportTable = new ReportTable(1,"NewsManagement",6,2,3,4);
        //newsServices.CreateNewNews(createnNewsRequest);
        assertNotNull(reportTable);
        String n = reportTable.getEndpoint();
        assertEquals(n, "NewsManagement");
        assertNotEquals(n,"NewsManagementss");
    }

    @Test
    void deletePolicy() {
        
    }

    @Test
    void searchPolicies() {
    }

    @Test
    void getAllPolicies() {
    }

    @Test
    void getPolicyById() {
    }
}