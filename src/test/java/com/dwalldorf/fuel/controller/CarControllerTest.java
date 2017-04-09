package com.dwalldorf.fuel.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.form.car.CarForm;
import com.dwalldorf.fuel.service.CarService;
import com.dwalldorf.fuel.service.UserService;
import java.util.Map;
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
        final String expectedViewName = carController.listPage();
        final String actualViewName = carController.indexAction();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testListPage_ViewName() {
        final String expectedViewName = "/car//list";
        final String actualViewName = carController.listPage();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testAddPage_ViewName() {
        final String expectedViewName = "/car//edit";
        final String actualViewName = carController.addPage().getViewName();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testAddPage_CarFormModel() {
        final String expectedModelName = "carForm";
        final Map<String, Object> model = carController.addPage().getModel();

        assertTrue(model.containsKey(expectedModelName));

        final Object carForm = model.get(expectedModelName);
        assertNotNull(carForm);
        assertTrue(carForm instanceof CarForm);
    }
}