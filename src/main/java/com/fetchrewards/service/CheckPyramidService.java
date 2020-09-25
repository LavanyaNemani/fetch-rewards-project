package com.fetchrewards.service;

import com.fetchrewards.controller.FetchRewardsController;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Logger;

@Component
public class CheckPyramidService {

    Logger logger = Logger.getLogger(CheckPyramidService.class.getName());


    public boolean isPyramid(String input) {

        logger.info("In isPyramid service-method: " + input);

        HashMap<Character, Integer> hashMap = new HashMap<>();
        boolean isGivenInputPyramid = false;
        if (input.isEmpty()) {
            isGivenInputPyramid = false;
        } else {
            for (int i = 0; i < input.length(); i++) {
                char value = input.toLowerCase().charAt(i);
                if(Character.isLetter(value)) {
                    if (hashMap.containsKey(value)) {
                        int count = hashMap.get(value);
                        hashMap.put(value, count + 1);
                    } else {
                        hashMap.put(input.toLowerCase().charAt(i), 1);
                    }
                }
            }
            logger.info("Hashmap size " + hashMap.size());

            ArrayList<Integer> charValue = new ArrayList(hashMap.values());
            Collections.sort(charValue);

           boolean isFrequencyIncreasingOrder =  isFrequencyIncreasingOrder(charValue);
           if(isFrequencyIncreasingOrder){
               isGivenInputPyramid = true;
           }
        }
        return isGivenInputPyramid;
    }

    private boolean isFrequencyIncreasingOrder(ArrayList<Integer> hashMapValues) {
        boolean isFrequencyIncreasingOrder = false;
        for (int i = 0; i <hashMapValues.size()-1; i++) {
            int value = hashMapValues.get(i + 1) - hashMapValues.get(i);
            if (value == 1) {
                isFrequencyIncreasingOrder= true;
            } else {
                isFrequencyIncreasingOrder = false;
            }
        }
        logger.info("isFrequencyIncreasingOrder " + isFrequencyIncreasingOrder);
        return isFrequencyIncreasingOrder;
    }
}
