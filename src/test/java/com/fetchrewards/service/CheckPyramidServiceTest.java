package com.fetchrewards.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckPyramidServiceTest {

    CheckPyramidService checkPyramidService = new CheckPyramidService();

    @Test
    public void testIsPyramid_Valid() {
        boolean result = checkPyramidService.isPyramid("banana");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsPyramid_InValid() {
        boolean result = checkPyramidService.isPyramid("people");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsPyramid_NullValue() {
        boolean result = checkPyramidService.isPyramid("");
        Assert.assertEquals(false, result);
    }

    /*
    Considers this true because: it ignores all the numbers and special chars.
    Hence it only contains string "abbccc" which is a pyramid

     */
    @Test
    public void testIsPyramid_WithNumbersAndSpecialChars() {
        boolean result = checkPyramidService.isPyramid("!!@@1234a$$bbccc");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsPyramid_OnlyOneKindOfLetter() {
        boolean result = checkPyramidService.isPyramid("aaaaa");
        Assert.assertEquals(false, result);
    }

}
