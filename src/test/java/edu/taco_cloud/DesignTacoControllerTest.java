package edu.taco_cloud;

import edu.taco_cloud.controllers.DesignTacoController;
import edu.taco_cloud.models.Taco;
import edu.taco_cloud.models.TacoOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DesignTacoController.class)
class DesignTacoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testDesignPage() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("designPage"))
                .andExpect(content().string(
                        containsString("Design your taco!")
                ));

    }

    @Test
    void testTacoAddedToModelAttribute() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(model().attribute("taco", equalTo(new Taco())))
                .andExpect(model().attribute("tacoOrder", equalTo(new TacoOrder())))
                .andExpect(model().attribute("wrap", hasSize(2)))
                .andExpect(model().attribute("protein", hasSize(2)))
                .andExpect(model().attribute("sauce", hasSize(2)));
    }
}
