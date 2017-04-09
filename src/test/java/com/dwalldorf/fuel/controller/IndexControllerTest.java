package com.dwalldorf.fuel.controller;

import static org.junit.Assert.assertEquals;

import com.dwalldorf.fuel.BaseTest;
import org.junit.Test;

public class IndexControllerTest extends BaseTest {

    private IndexController indexController;

    @Override
    protected void setUp() {
        this.indexController = new IndexController();
    }

    @Test
    public void testIndexPage_ViewName() {
        final String expectedViewName = "/index";
        final String actualViewName = indexController.indexPage();

        assertEquals(expectedViewName, actualViewName);
    }
}