package com.tecnotree.demo.api.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnotree.demo.api.comment.dto.CommentRequestDto;
import com.tecnotree.demo.api.common.PageRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {


    @Autowired
    MockMvc mockMvc;


    @Test
    void getAllByPaging_Correct()  throws Exception {
        mockMvc.perform(get("/comments")
                .content(asJsonString(getPageRequestDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private PageRequestDto getPageRequestDto() {
        return PageRequestDto.newInstance().setPage(0).setSize(10).build();
    }


    @Test
    void getById_Correct() throws Exception {
        mockMvc.perform(get("/comments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void newComment_Correct() throws Exception {
        mockMvc.perform(post("/comments")
                .content(asJsonString(getNewComment()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private CommentRequestDto getNewComment() {
        return CommentRequestDto.newInstance()
                .setPostId(1L)
                .setName("odio adipisci rerum aut animi")
                .setEmail("Nikita@garfield.biz")
                .setBody("quia molestiae reprehenderit quasi aspernatur\naut expedita occaecati aliquam eveniet laudantium\nomnis quibusdam delectus saepe quia accusamus maiores nam est\ncum et ducimus et vero voluptates excepturi deleniti ratione")
                .build();
    }

    @Test
    void updateComment_Correct() throws Exception {
        mockMvc.perform(patch("/comments/1")
                .content(asJsonString(getUpdateComment()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Object getUpdateComment() {
        return CommentRequestDto.newInstance()
                .setPostId(1L)
                .setName("odio adipisci rerum aut animi")
                .setEmail("Nikita@garfield.biz")
                .setBody("quia molestiae reprehenderit quasi aspernatur\naut expedita occaecati aliquam eveniet laudantium\nomnis quibusdam delectus saepe quia accusamus maiores nam est\ncum et ducimus et vero voluptates excepturi deleniti ratione")
                .build();
    }

    @Test
    void deleteComment_Correct() throws Exception {
        mockMvc.perform(delete("/comments/2")
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
