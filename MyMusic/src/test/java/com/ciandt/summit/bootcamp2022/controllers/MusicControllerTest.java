package com.ciandt.summit.bootcamp2022.controllers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ciandt.summit.bootcamp2022.application.adapters.controllers.MusicController;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import com.ciandt.summit.bootcamp2022.infra.config.TokenConfiguration;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicDTO;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import java.util.*;

@WebMvcTest(MusicController.class)
@SpringBootTest
public class MusicControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private MusicController musicController;
    @MockBean
    private MusicServicePort musicServicePortMock;
    @MockBean
    private TokenConfiguration configuration;

    @Test
    void findMusicByNameTest_Success() throws Exception {
        /*Mockito.when(musicController.findMusicByName("u2")).thenReturn(ResponseEntity.ok(setMusicsDTOEsperado));
        RequestBuilder requestBuilder = get("api/musicas/musics?name=u2");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(ResponseEntity.ok(setMusicsDTOEsperado), mvcResult);*/
        /*mockMvc.perform(MockMvcRequestBuilders.get("/music?name=u2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().;*/

    }

    @Test
    void getMusicTest() throws Exception {
       /*Mockito.when(musicController.getMusic()).thenReturn(new ResponseEntity<Set<MusicDTO>>(HttpStatus.OK));
       RequestBuilder requestBuilder = get("https://localhost:8788/api/musicas/all");
       MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
       Assertions.assertEquals(requestBuilder, mvcResult);*/
       ResultActions resultActions =
                mockMvc.perform(get("https://localhost:8708/api/musicas/all").accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void shouldReturn200WithoutFilter() throws Exception {
        //List<MusicDTO> musics = new ArrayList<>(Arrays.asList(music1, music2, music3));


        when(musicServicePortMock.getMusicsByFilter("")).thenThrow(new InvalidParameterException("O termo buscado deve ter pelo menos 2 caracteres."));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/musicas")
                        .header()
                        .param("name", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void findMusicByNameTest_Failure_NameNull() throws Exception {
        when(musicController.findMusicByName(null)).thenThrow(new Exception()); // trocar exception pelas exceptions criadas
        RequestBuilder requestBuilder = get("api/musicas/musics?name=");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(requestBuilder, mvcResult);
    }

    @Test
    void findMusicByNameTest_Failure_NameWithLessOfTwoCharacters() throws Exception {
        when(musicController.findMusicByName("u")).thenThrow(new Exception()); // trocar exception pelas exceptions criadas
        RequestBuilder requestBuilder = get("api/musicas/musics?name=u");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(requestBuilder, mvcResult);
    }

//    @Test void addMusicsTest() throws Exception {
//        MusicDTO musicDTO = new MusicDTO("1", "Sunday Blood Sunday", "32844fdd-bb76-4c0a-9627-e34ddc9fd892");
//        Mockito.when(musicController.addMusics(musicDTO)).;
//    }

}