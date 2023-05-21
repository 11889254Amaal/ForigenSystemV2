package com.example.Foreign.Affairs.Ministry.API.Controller;

import com.example.Foreign.Affairs.Ministry.API.Modell.News;
import com.example.Foreign.Affairs.Ministry.API.Modell.ReportTable;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.ReportRepository;
import com.example.Foreign.Affairs.Ministry.API.Services.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NewsManagementTest {
@Autowired
NewsManagement newsManagement;
    @Autowired
    ReportRepository reportRepository;


    @Autowired
    ReportService reportService;
    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deletePolicy() {
    }

    @Test
    void searchNews() {
    }

    @Test
    void getAllNews() {
        ResponseEntity<String> responseEntity=ResponseEntity.ok().build();
        assertNotNull(responseEntity);
        assert(responseEntity.getStatusCode().equals(HttpStatus.OK));
      

    }



    @Test
    void getNewsById() {
    }
}