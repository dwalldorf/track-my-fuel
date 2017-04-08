package com.dwalldorf.fuel.util;

import static org.junit.Assert.assertEquals;

import com.dwalldorf.fuel.BaseTest;
import org.junit.Test;

public class RouteUtilTest extends BaseTest {

    @Test
    public void testRedirectString() {
        final String input = "/home";
        final String expectedOutput = "redirect:" + input;

        String output = RouteUtil.redirectString(input);

        assertEquals(expectedOutput, output);
    }
}