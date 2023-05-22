package com.example.Foreign.Affairs.Ministry.API.Controller;

import com.example.Foreign.Affairs.Ministry.API.Modell.ReportTable;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.CreatenNewsRequest;
import com.example.Foreign.Affairs.Ministry.API.Services.NewsServices;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewsManagementTest {

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
        assertNotNull(reportTable);
        String n = reportTable.getEndpoint();
        Integer m=reportTable.getId();
        assertEquals(n, "NewsManagement");
        assertNotEquals(n,"NewsManagementss");
        assertEquals(1,1);
    }

    @Test
    void deletePolicy() {
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
    void searchNews() {
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
    void getAllNews() {
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
    void getNewsById() {
        NewsServices newsServices = new NewsServices();
        CreatenNewsRequest createnNewsRequest = new CreatenNewsRequest();
        ReportTable reportTable = new ReportTable(1,"NewsManagement",6,2,3,4);
        //newsServices.CreateNewNews(createnNewsRequest);
        assertNotNull(reportTable);
        String n = reportTable.getEndpoint();
        assertEquals(n, "NewsManagement");
        assertNotEquals(n,"NewsManagementss");
    }
}