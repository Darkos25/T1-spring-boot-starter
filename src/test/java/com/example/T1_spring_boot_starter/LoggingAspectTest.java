package com.example.T1_spring_boot_starter;

import com.example.T1_spring_boot_starter.config.LoggingAutoConfiguration;
import com.example.T1_spring_boot_starter.config.LoggingProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {T1SpringBootStarterApplication.class, LoggingAutoConfiguration.class})
@AutoConfigureMockMvc
public class LoggingAspectTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoggingProperties loggingProperties;

    @BeforeEach
    public void setUp() {
        loggingProperties.setLogRequest(true);
        loggingProperties.setLogResponse(true);
    }

    @Test
    public void testLogRequestAndResponse() throws Exception {

        mockMvc.perform(get("/test-endpoint"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testLogRequestDisabled() throws Exception {

        loggingProperties.setLogRequest(false);

        mockMvc.perform(get("/test-endpoint"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testLogResponseDisabled() throws Exception {

        loggingProperties.setLogResponse(false);

        mockMvc.perform(get("/test-endpoint"))
                .andExpect(status().isNotFound());

    }
}
