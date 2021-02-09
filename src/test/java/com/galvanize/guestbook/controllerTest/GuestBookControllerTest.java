package com.galvanize.guestbook.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.model.GuestComment;
import com.galvanize.guestbook.repository.GuestBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class GuestBookControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuestBookRepository guestBookRepository;

    GuestComment guestComment1, guestComment2;
    @BeforeEach
    public void setUp(){
        guestComment1 = new GuestComment("Ashwini", "hello");
        guestComment2 = new GuestComment("Sudha", "Beautiful place");
        guestBookRepository.deleteAll();
    }

    @Test
    public void addGuestComment() throws Exception {
        String guestCommentJson = objectMapper.writeValueAsString(guestComment1);

        mockMvc.perform(post("/guestbook/guestcomment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(guestCommentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(guestComment1.getName()))
                .andExpect(jsonPath("$.comment").value(guestComment1.getComment()))

                .andDo(document("addGuestComment", responseFields(
                        fieldWithPath("name").description("name of the guest"),
                        fieldWithPath("comment").description("Guest comment")

                )));
    }


    @Test
    public void getAllComments() throws Exception {
        guestBookRepository.save(guestComment1);
        guestBookRepository.save(guestComment2);

        mockMvc.perform(get("/guestbook/guestcomment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value(guestComment1.getName()))
                .andExpect(jsonPath("[0].comment").value(guestComment1.getComment()))
                .andExpect(jsonPath("[1].name").value(guestComment2.getName()))
                .andExpect(jsonPath("[1].comment").value(guestComment2.getComment()))
                .andDo(document("getAllComments", responseFields(
                        fieldWithPath("[0].name").description("name of the guest"),
                        fieldWithPath("[0].comment").description("Guest comment"),
                        fieldWithPath("[1].name").description("name of the guest"),
                        fieldWithPath("[1].comment").description("Guest comment")

                )));
    }
}
