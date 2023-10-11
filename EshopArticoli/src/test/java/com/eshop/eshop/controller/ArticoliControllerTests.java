package com.eshop.eshop.controller;

import com.eshop.eshop.entity.Articolo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ArticoliController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticoliControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticoliService articoliService;

    @Autowired
    private ObjectMapper objectMapper;

    private Articolo articolo;

    private ArticoloDto articoloDto;

    @BeforeEach
    public void init(){
        articolo = new Articolo("iphone 30", "Smartphone, 4k resolution, 50GB RAM, 10TB internal memory",
                3900.99);
    }

    @Test
    @Order(1)
    public void ArticoliController_CreateArticolo_ReturnCreated() throws Exception {

        given(articoliService.createArticolo(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("api/articoli/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ArticoloDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
