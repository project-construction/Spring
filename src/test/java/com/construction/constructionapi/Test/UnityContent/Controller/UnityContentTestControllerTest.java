package com.construction.constructionapi.Test.UnityContent.Controller;

import com.construction.constructionapi.Test.Domain.Score;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UnityContentTestController.class)
class UnityContentTestControllerTest {
    @Autowired
    private MockMvc mvc;
    @Before
    public void createController() {
        mvc = MockMvcBuilders.standaloneSetup().build();
    }
    @Test
    void allTestEntity() throws Exception {
        Score score = new Score();
        int hammering = 10;
        int nBack = 100;
        mvc.perform(post("/UnityContent/insertContent"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.hammering", is(hammering)))
                .andExpect((ResultMatcher) jsonPath("$.nBack", is(nBack)));
    }

    @Test
    void insertContent() {
    }
}