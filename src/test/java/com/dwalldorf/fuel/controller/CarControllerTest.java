package com.dwalldorf.fuel.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.exception.NotFoundException;
import com.dwalldorf.fuel.form.car.CarForm;
import com.dwalldorf.fuel.model.Car;
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
        final String expectedViewName = "/car/list";
        final String actualViewName = carController.listPage();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testAddPage_ViewName() {
        final String expectedViewName = "/car/edit";
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

    @Test(expected = NotFoundException.class)
    public void testEditPage_ThrowsNotFound() {
        final String id = "noSuchId";
        when(mockCarService.findById(id)).thenReturn(null);

        carController.editPage(id);
    }

    @Test
    public void testEditPage_VerifiesOwner() {
        final String id = "someId";
        final Car mockPersistedCar = new Car().setId(id);
        when(mockCarService.findById(eq(id))).thenReturn(mockPersistedCar);

        carController.editPage(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedCar));
    }

    @Test
    public void testEditPage_ViewName() {
        final String id = "someId";
        when(mockCarService.findById(eq(id))).thenReturn(new Car().setId(id));
        final String expectedViewName = "/car/edit";

        final String actualViewName = carController.editPage(id).getViewName();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testEditPage_CarFormModel() {
        final String id = "123";
        when(mockCarService.findById(eq(id))).thenReturn(new Car());

        final String expectedModelName = "carForm";
        final Map<String, Object> modelMap = carController.editPage(id).getModel();

        assertTrue(modelMap.containsKey(expectedModelName));

        final Object carForm = modelMap.get(expectedModelName);
        assertTrue(carForm instanceof CarForm);
        assertNotNull(carForm);
    }
}