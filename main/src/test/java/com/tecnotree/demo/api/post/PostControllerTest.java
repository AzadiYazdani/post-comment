package com.tecnotree.demo.api.post;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnotree.demo.api.post.dto.PageRequestDto;
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
    PostController postController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllByPaging() throws Exception {
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
    void getById() throws Exception {
        mockMvc.perform(get("/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllComments()  throws Exception {
            mockMvc.perform(get("/posts/1/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    void search() throws Exception {
        mockMvc.perform(get("/posts?title=eos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void newPost() throws Exception {
        mockMvc.perform(post("/posts")
                .content(asJsonString(getNewPost()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private PostRequestDto getNewPost() {
        return PostRequestDto.newInstance()
                .setTitle("quasi id et eos tenetur aut quo autem")
                .setBody("eum sed dolores ipsam sint possimus debitis occaecati\ndebitis qui qui et\nut placeat enim earum aut odit facilis\nconsequatur suscipit necessitatibus rerum sed inventore temporibus consequatur")
                .setUserId(3)
                .build();
    }

    @Test
    void updatePost() throws Exception {
        mockMvc.perform(patch("/posts/1")
                .content(asJsonString(getUpdatePost()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private PostRequestDto getUpdatePost() {
        return PostRequestDto.newInstance()
                .setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto")
                .setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                .setUserId(1)
                .build();
    }

    @Test
    void deletePost() throws Exception {
        mockMvc.perform(delete("/posts/3")
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
