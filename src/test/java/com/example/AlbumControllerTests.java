package com.example;

import com.example.interfaces.AlbumRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Random;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;



/**
 * Created by Skinner on 2/25/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
public class AlbumControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    AlbumRepository repository;

    @Test
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/albums/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Bob\", \"bandName\": \"The Builder\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Bob")));

        verify(this.repository).save(any(Album.class));
    }

    @Test
    public void testGetList() throws Exception {
        Long id = new Random().nextLong();
        Album album = new Album();
        album.setName("Billy");
        album.setBandName("Goat");
        album.setId(id);

        when(this.repository.findAll()).thenReturn(Collections.singletonList(album));

        MockHttpServletRequestBuilder request = get("/albums")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(id)))
                .andExpect(jsonPath("$[0].name", equalTo("Billy")))
                .andExpect(jsonPath("$[0].bandName", equalTo("Goat")));
    }
}
