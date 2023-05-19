import com.example.Foreign.Affairs.Ministry.API.Controller.NewsManagement;
import com.example.Foreign.Affairs.Ministry.API.Mailling.EmailService;
import com.example.Foreign.Affairs.Ministry.API.ScheduleJobs.Schedule;
import com.example.Foreign.Affairs.Ministry.API.Services.NewsServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(NewsManagement.class)
@ContextConfiguration(classes = NewsManagement.class)
public class NewsManagementTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private NewsServices newsServices;

    @Mock
    private EmailService emailService;

    @Mock
    private Schedule scheduleDetails;

    @InjectMocks
    private NewsManagement newsManagement;

    @Test
    public void testCreateNews() throws Exception {
        // Prepare your request payload
        String requestPayload = "{\"property\":\"value\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/createNews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("new News add Successfully "));
    }

    @Test
    public void testUpdateNews() throws Exception {
        // Prepare your request payload
        String requestPayload = "{\"property\":\"value\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/updateNews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("News Information updated Successfully "));
    }

    @Test
    public void testDeleteNews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deleteNews")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("News deleted Successfully "));
    }

    @Test
    public void testSearchNews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .param("countryName", "USA")
                        .param("regionName", "Europe")
                        .param("topicOfNews", "Technology"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().json(Collections.emptyList().toString()));
    }
}
