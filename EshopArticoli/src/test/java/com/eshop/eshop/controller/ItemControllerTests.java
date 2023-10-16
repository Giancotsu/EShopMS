package com.eshop.eshop.controller;

import com.eshop.eshop.controllers.ItemController;
import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.models.ItemEntity;
import com.eshop.eshop.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = ItemController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    private ItemEntity item;

    private ItemDto itemDto;

    @BeforeEach
    public void init(){
        item = new ItemEntity("iphone 30", "Smartphone, 4k resolution, 50GB RAM, 10TB internal memory", BigDecimal.valueOf(13000.50));
    }

    @Test
    @Order(1)
    public void ArticoliController_CreateArticolo_ReturnCreated() throws Exception {

        //given(itemService.createItem(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("api/items/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ItemDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
