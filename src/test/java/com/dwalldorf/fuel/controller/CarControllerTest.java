package com.dwalldorf.fuel.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.exception.NotFoundException;
import com.dwalldorf.fuel.form.car.CarForm;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.service.CarService;
import com.dwalldorf.fuel.service.UserService;
import com.dwalldorf.fuel.util.RouteUtil;
import java.util.Map;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

public class CarControllerTest extends BaseTest {

    private static final Long userId = 2184L;
    private static final Long id = 73123L;

    private static final User mockUser = new User().setId(userId);
    private static final Car mockCar = new Car().setId(id).setUser(mockUser);
    private static final CarForm mockCarForm = new CarForm().setId(id).setUserId(userId);

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
        when(mockCarService.findById(id)).thenReturn(null);
        carController.editPage(id);
    }

    @Test
    public void testEditPage_VerifiesOwner() {
        when(mockCarService.findById(eq(id))).thenReturn(mockCar);

        carController.editPage(id);

        verify(mockUserService).verifyOwner(eq(mockCar));
    }

    @Test
    public void testEditPage_ViewName() {
        when(mockCarService.findById(eq(id))).thenReturn(new Car().setId(id).setUser(mockUser));
        when(mockUserService.findById(eq(mockUser.getId()))).thenReturn(mockUser);
        final String expectedViewName = "/car/edit";

        final String actualViewName = carController.editPage(id).getViewName();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testEditPage_CarFormModel() {
        when(mockCarService.findById(eq(id))).thenReturn(mockCar);
        when(mockCarService.fromModel(mockCar)).thenReturn(mockCarForm);

        final String expectedModelName = "carForm";
        final Map<String, Object> modelMap = carController.editPage(id).getModel();

        assertTrue(modelMap.containsKey(expectedModelName));

        final Object carForm = modelMap.get(expectedModelName);
        assertTrue(carForm instanceof CarForm);
        assertNotNull(carForm);
    }

    @Test(expected = NotFoundException.class)
    public void testSaveAction_ThrowsNotFound() {
        when(mockCarService.toModel(any(CarForm.class))).thenReturn(mockCar);
        when(mockCarService.findById(eq(id))).thenReturn(null);

        CarForm refuelingForm = new CarForm().setId(id);
        carController.saveAction(refuelingForm);
    }

    @Test
    public void testSaveAction_VerifiesOwner() {
        when(mockCarService.toModel(any(CarForm.class))).thenReturn(mockCar);
        when(mockCarService.findById(eq(id))).thenReturn(mockCar);

        carController.saveAction(mockCarForm);

        verify(mockUserService).verifyOwner(eq(mockCar));
    }

    @Test
    public void testSaveAction_NewEntry_SetsUserId() {
        when(mockCarService.toModel(any(CarForm.class))).thenReturn(new Car());
        when(mockUserService.getCurrentUserId()).thenReturn(userId);
        when(mockUserService.findById(userId)).thenReturn(mockUser);
        when(mockUserService.getCurrentUser()).thenReturn(mockUser);

        ArgumentCaptor<Car> carCaptor = ArgumentCaptor.forClass(Car.class);
        carController.saveAction(new CarForm());

        verify(mockCarService).save(carCaptor.capture());
        final Car capturedCar = carCaptor.getValue();

        assertNotNull(capturedCar.getUser());
        assertEquals(userId, capturedCar.getUser().getId());
    }

    @Test
    public void testSaveAction_Redirect() {
        when(mockCarService.toModel(any(CarForm.class))).thenReturn(mockCar);
        when(mockCarService.findById(eq(id))).thenReturn(mockCar);

        final String expectedRedirect = RouteUtil.redirectString("/cars");
        final String actualRedirect = carController.saveAction(new CarForm());

        assertEquals(expectedRedirect, actualRedirect);
    }
}