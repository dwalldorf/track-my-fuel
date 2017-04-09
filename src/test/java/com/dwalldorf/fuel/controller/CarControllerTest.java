package com.dwalldorf.fuel.controller;

import static org.junit.Assert.assertEquals;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.service.CarService;
import com.dwalldorf.fuel.service.UserService;
import org.junit.Test;
import org.mockito.Mock;

public class CarControllerTest extends BaseTest {

    @Mock
    private CarService mockCarService;

    @Mock
    private UserService mockUserService;

    private CarController carController;

    @Override
    protected void setUp() {
        this.carController = new CarController(mockCarService, mockUserService);
    }

    @Test
    public void testIndexAction_ReturnsListPage() {
        final String expectedViewName = carController.listAction();
        final String actualViewName = carController.indexAction();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testListPage_ViewName() {
        final String expectedViewName = "/car//list";
        final String actualViewName = carController.listAction();

        assertEquals(expectedViewName, actualViewName);
    }
}