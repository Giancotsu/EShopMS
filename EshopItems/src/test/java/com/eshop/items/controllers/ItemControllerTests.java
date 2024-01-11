package com.eshop.items.controllers;

import com.eshop.items.EshopApplication;
import com.eshop.items.dto.ItemDto;
import com.eshop.items.dto.ItemResponse;
import com.eshop.items.entities.ItemCategoryEntity;
import com.eshop.items.entities.ItemEntity;
import com.eshop.items.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@WebMvcTest(controllers = ItemController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)

@ContextConfiguration(classes = EshopApplication.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    private ItemEntity item;

    private ItemDto itemDto;

    @BeforeEach
    public void init() throws JSONException, IOException {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        item = new ItemEntity("iphone 30", "Smartphone, 4k resolution, 50GB RAM, 10TB internal memory");

        Set<ItemCategoryEntity> categories = new HashSet<>();
        ItemCategoryEntity category = new ItemCategoryEntity();
        category.setCategoryId(8);
        category.setCategoryName("COMPUTER");
        categories.add(category);

        itemDto = new ItemDto("iphone 30", "Smartphone, 4k resolution, 50GB RAM, 10TB internal memory", BigDecimal.valueOf(50), categories);
    }

    @Test
    @Order(1)
    public void ItemController_CreateItem_ReturnCreated() throws Exception {

        given(itemService.createItem(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:9000/api/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(itemDto.getName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    public void ItemController_GetAllItems_ReturnItemResponse() throws Exception {

        ItemResponse itemResponse = ItemResponse.builder().pageSize(10).last(true).pageNumber(1).content(Arrays.asList(itemDto)).build();
        when(itemService.getAllItems(1, 10)).thenReturn(itemResponse);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9000/api/item")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNumber", "1")
                .param("pageSize", "10"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(itemResponse.getContent().size())));
    }

    @Test
    @Order(3)
    public void ItemController_GetItemById_ReturnItemDto() throws Exception {
        int itemId = 1;
        when(itemService.getItemById(itemId)).thenReturn(itemDto);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9000/api/item/" + itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(itemDto.getName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(4)
    public void ItemController_DeleteItem_ReturnString() throws Exception {

        int itemId = 1;
        doNothing().when(itemService).deleteItemById(itemId);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:9000/api/item/"+itemId+"/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
