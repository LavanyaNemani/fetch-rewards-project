package com.fetchrewards.controller;

import com.fetchrewards.service.CheckPyramidService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(FetchRewardsController.class)
public class FetchRewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CheckPyramidService checkPyramidService;

    @Test
    public void isPyramid_Valid() throws Exception {

        String input = "banana";
        String url = getUrl();

        Mockito.when(checkPyramidService.isPyramid(input)).thenReturn(true);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.ALL)
                .content(input)).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        String expected = "{\"statusMessage\":\"Given string is pyramid\"}";
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(200, statusCode);
        Assert.assertEquals(expected, responseBody);
    }

    @Test
    public void isPyramid_InValid() throws Exception {

        String input = "people";
        String url = getUrl();

        Mockito.when(checkPyramidService.isPyramid(input)).thenReturn(false);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.ALL)
                .content(input)).andReturn();

        String expected = "{\"statusMessage\":\"Given string is not a pyramid\"}";
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(expected, responseBody);
    }

    @Test
    public void isPyramid_BadRequest() throws Exception {

        String input = "";
        String url = getUrl();

        Mockito.when(checkPyramidService.isPyramid(input)).thenReturn(false);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.ALL)
                .content(input)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(400, status);
    }

    public String getUrl() {
        String url = "http://localhost:8080/string/is-pyramid";
        return url;
    }


}
