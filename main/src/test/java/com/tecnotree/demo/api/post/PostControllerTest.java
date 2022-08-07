package com.tecnotree.demo.api.post;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnotree.demo.api.common.PageRequestDto;
import com.tecnotree.demo.api.post.dto.PostRequestDto;
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
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllByPaging_Correct() throws Exception {
        mockMvc.perform(get("/posts")
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
        mockMvc.perform(get("/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllComments_Correct() throws Exception {
        mockMvc.perform(get("/posts/1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void search_Correct() throws Exception {
        mockMvc.perform(get("/posts?title=eos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void search_Error() throws Exception {
        mockMvc.perform(get("/posts?title=eeos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void newPost_Correct() throws Exception {
        mockMvc.perform(post("/posts")
                .content(asJsonString(getNewPost_correct()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private PostRequestDto getNewPost_correct() {
        return PostRequestDto.newInstance()
                .setTitle("quasi id et eos tenetur aut quo autem")
                .setBody("eum sed dolores ipsam sint possimus debitis occaecati\ndebitis qui qui et\nut placeat enim earum aut odit facilis\nconsequatur suscipit necessitatibus rerum sed inventore temporibus consequatur")
                .setUserId(4L)
                .build();
    }

    @Test
    void newPost_Error() throws Exception {
        mockMvc.perform(post("/posts")
                .content(asJsonString(getNewPost_Error()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    private PostRequestDto getNewPost_Error() {
        return PostRequestDto.newInstance()
                .setTitle("quasi id et eos tenetur aut quo autem")
                .setBody("eum sed dolores ipsam sint possimus debitis occaecati\ndebitis qui qui et\nut placeat enim earum aut odit facilis\nconsequatur suscipit necessitatibus rerum sed inventore temporibus consequatur")
                .setUserId(-4L)
                .build();
    }

    @Test
    void updatePost_Correct() throws Exception {
        mockMvc.perform(patch("/posts/1")
                .content(asJsonString(getUpdatePost()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePost_Error() throws Exception {
        mockMvc.perform(patch("/posts/1")
                .content(asJsonString(getUpdatePost()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    private PostRequestDto getUpdatePost() {
        return PostRequestDto.newInstance()
                .setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto")
                .setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                .setUserId(-1L)
                .build();
    }

    @Test
    void deletePost_Correct() throws Exception {
        mockMvc.perform(delete("/posts/12")
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
