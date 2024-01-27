package com.haraji.sale.api.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haraji.sale.api.common.PageRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllByPaging_Correct() throws Exception {
        mockMvc.perform(get("/todos/")
                .content(asJsonString(getPageRequestDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private PageRequestDto getPageRequestDto() {
        return PageRequestDto.newInstance().setPage(0).setSize(10).build();
    }

    @Test
    void getByUserId_Correct() throws Exception {
        mockMvc.perform(get("/todos?userId=1&completed=true")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
