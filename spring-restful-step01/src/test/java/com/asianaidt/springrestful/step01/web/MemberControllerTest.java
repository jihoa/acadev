package com.asianaidt.springrestful.step01.web;

import com.asianaidt.springrestful.step01.repository.MemberRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebMvcTest
class MemberControllerTest {
    @MockBean
    MemberRepository memberRepository;
    @Autowired
    MemberController memberController;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenUserControllerInjected_thenNotNull()  {
        assertThat(memberController).isNotNull();
    }

    @Test
    public void whenGetRequestToMembers_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/members")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void whenPostRequestToMembersAndValidUser_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String member = "{\"username\":\"yhkim\",\"password\":\"12345678\",\"email\":\"yuri56@asianaidt.com\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                .content(member)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
    }

    @Test
    public void whenPostRequestToUsersAndInValidUser_thenCorrectResponse() throws Exception {
        String member = "{\"username\":\" \",\"password\":\"12345678\",\"email\":\"bob@asianaidt.com\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                .content(member)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Is.is("?????????????????? ?????? ?????? ???????????????.")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}

