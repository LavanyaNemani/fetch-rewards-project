package com.fetchrewards.controller;

import com.fetchrewards.pojo.FetchRewardsResponse;
import com.fetchrewards.service.CheckPyramidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.logging.Logger;

@Controller
@RequestMapping("/string")
public class FetchRewardsController {

    public static final String GIVEN_STRING_IS_PYRAMID = "Given string is pyramid";
    public static final String GIVEN_STRING_IS_NOT_A_PYRAMID = "Given string is not a pyramid";

    @Autowired
    CheckPyramidService checkPyramidService;

    Logger logger = Logger.getLogger(FetchRewardsController.class.getName());


    @RequestMapping(value = {"/is-pyramid"}, method = {RequestMethod.POST})
    @ResponseBody

    public ResponseEntity isPyramid(@RequestBody String input) {

        logger.info("In isPyramid method: " + input);
        FetchRewardsResponse rewardsResponse = new FetchRewardsResponse();

        boolean isTrue = checkPyramidService.isPyramid(input);
        if (isTrue) {
            rewardsResponse.setStatusMessage(GIVEN_STRING_IS_PYRAMID);
            return ResponseEntity.ok(rewardsResponse);

        } else {
            rewardsResponse.setStatusMessage(GIVEN_STRING_IS_NOT_A_PYRAMID);
            return ResponseEntity.ok(rewardsResponse);
        }

    }

}
