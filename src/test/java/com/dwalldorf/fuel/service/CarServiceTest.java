package com.dwalldorf.fuel.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.form.car.CarForm;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.repository.CarRepository;
import org.junit.Test;
import org.mockito.Mock;

public class CarServiceTest extends BaseTest {

    private static final Long id = 123L;
    private static final Long userId = 313213L;
    private static final String manufacturer = "Subaru";
    private static final String modelName = "Levorg";
    private static final String year = "2015";
    private static final String licensePlate = "B-dsaasd";

    @Mock
    private UserService mockUserService;

    @Mock
    private CarRepository mockCarRepository;

    private CarService carService;

    @Override
    protected void setUp() {
        this.carService = new CarService(mockUserService, mockCarRepository);
    }

    @Test
    public void testToModel() {
        final User mockUser = new User().setId(userId);
        when(mockUserService.findById(userId)).thenReturn(mockUser);

        final CarForm form = new CarForm()
                .setId(id)
                .setUserId(userId)
                .setManufacturer(manufacturer)
                .setModelName(modelName)
                .setYear(year)
                .setLicensePlate(licensePlate);

        Car model = carService.toModel(form);

        assertEquals(id, model.getId());
        assertEquals(userId, model.getUser().getId());
        assertEquals(manufacturer, model.getManufacturer());
        assertEquals(modelName, model.getModelName());
        assertEquals(year, model.getYear());
        assertEquals(licensePlate, model.getLicensePlate());
    }

    @Test
    public void testFromModel_WithNull() {
        final CarForm form = carService.fromModel(null);
        assertNull(form);
    }

    @Test
    public void testFromModel() {
        final Car model = new Car()
                .setId(id)
                .setUser(new User().setId(userId))
                .setManufacturer(manufacturer)
                .setModelName(modelName)
                .setYear(year)
                .setLicensePlate(licensePlate);
        final CarForm form = carService.fromModel(model);

        assertEquals(id, form.getId());
        assertEquals(userId, form.getUserId());
        assertEquals(manufacturer, form.getManufacturer());
        assertEquals(modelName, form.getModelName());
        assertEquals(year, form.getYear());
        assertEquals(licensePlate, form.getLicensePlate());
    }
}