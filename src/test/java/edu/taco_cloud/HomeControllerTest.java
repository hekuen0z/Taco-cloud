package edu.taco_cloud;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import edu.taco_cloud.controllers.WebConfigHomePage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHomePage() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("homePage"))
                .andExpect(content().string(
                        Matchers.containsString("Welcome")
                ));
    }
}
