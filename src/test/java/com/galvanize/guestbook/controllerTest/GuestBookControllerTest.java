package com.galvanize.guestbook.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.model.GuestComment;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class GuestBookControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addGuestComment() throws Exception {
        GuestComment guestComment = new GuestComment("Ashwini", "hello");
        String guestCommentJson = objectMapper.writeValueAsString(guestComment);

        mockMvc.perform(post("/guestbook/guestcomment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(guestCommentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(guestComment.getName()))
                .andExpect(jsonPath("$.comment").value(guestComment.getComment()))

                .andDo(document("addGuestComment", responseFields(
                        fieldWithPath("name").description("name of the guest"),
                        fieldWithPath("comment").description("Guest comment")

                )));
    }
}
