package com.example.gasolinetrackproject;

import com.example.gasolinetrackproject.service.FuelService;
import com.example.gasolinetrackproject.service.FuelStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GasolineTrackTests {

    MockMvc mockMvc;

    @Autowired
    FuelStationService fuelStationService;

    @Autowired
    FuelService fuelService;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetHomePage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/home");
        this.mockMvc.perform(homePageRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("fuelStations"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("fuelStationsMap"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cities"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("companies"))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }

    @Test
    public void testGetPricesPage() throws Exception{
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/prices");
        this.mockMvc.perform(homePageRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("fuels"))
                .andExpect(MockMvcResultMatchers.view().name("prices"));
    }
}
