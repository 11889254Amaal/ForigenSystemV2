package com.example.Foreign.Affairs.Ministry.API.Controller;


import com.example.Foreign.Affairs.Ministry.API.Modell.ReportTable;
import com.example.Foreign.Affairs.Ministry.API.Repsitory.ReportRepository;
import com.example.Foreign.Affairs.Ministry.API.RequestObject.CreatenNewsRequest;
import com.example.Foreign.Affairs.Ministry.API.Services.NewsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.NotNull;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.PersistenceException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsManagementTest {
    private MockMvc mockMvc;

    @Mock
    private NewsServices newsServicesMock;

    @Mock
    private ReportRepository reportRepositoryMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        NewsManagement newsController = new NewsManagement();
        newsController.newsServices = newsServicesMock;
        newsController.reportRepository = reportRepositoryMock;
        mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
    }

    @Test
    public void testCreateCustomer_Success() throws Exception {
        // Arrange
        CreatenNewsRequest request = new CreatenNewsRequest();
        request.setTopicOfNews("Test News");


        doNothing().when(newsServicesMock).CreateNewNews(null);



        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/createCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test News\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("New News added Successfully"));

        // Verify that the CreateNewNews method was called
        verify(newsServicesMock).CreateNewNews(request);
        verifyNoMoreInteractions(newsServicesMock);
        verifyNoInteractions(reportRepositoryMock);
    }

    @Test
    public void testCreateCustomer_Failure() throws Exception {
        // Arrange
        CreatenNewsRequest request = new CreatenNewsRequest();
        request.setTopicOfNews("Test News");


        when(reportRepositoryMock.getById(1)).thenReturn(new ReportTable());

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/createCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test News\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("News failed to add"));

        // Verify that the CreateNewNews and getById methods were called
        verify(newsServicesMock).CreateNewNews(request);
        verify(reportRepositoryMock).getById(1);
        verify(reportRepositoryMock).save(any(ReportTable.class));
        verifyNoMoreInteractions(newsServicesMock);
        verifyNoMoreInteractions(reportRepositoryMock);
    }
}
