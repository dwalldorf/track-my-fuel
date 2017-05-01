package com.dwalldorf.fuel.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.form.refueling.RefuelingForm;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.model.Refueling;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.repository.RefuelingRepository;
import org.junit.Test;
import org.mockito.Mock;

public class RefuelingServiceTest extends BaseTest {

    private static final Long id = 123L;
    private static final Long userId = 321L;
    private static final Long carId = 987L;
    private static final String comment = "comment";
    private static final Float liters = 33.3F,
            cost = 50.0F;
    private static final Long kilometers = 4000L;

    private static final User mockUser = new User().setId(userId);
    private static final Car mockCar = new Car().setId(carId);

    @Mock
    private RefuelingRepository mockRefuelingRepository;

    @Mock
    private ExpenseService mockExpenseService;

    @Mock
    private UserService mockUserService;

    @Mock
    private CarService mockCarService;

    private RefuelingService refuelingService;

    @Override
    protected void setUp() {
        this.refuelingService = new RefuelingService(
                mockRefuelingRepository,
                mockExpenseService,
                mockUserService,
                mockCarService
        );
    }

    @Test
    public void testToModel() {
        when(mockUserService.findById(eq(userId))).thenReturn(mockUser);
        when(mockCarService.findById(eq(carId))).thenReturn(mockCar);

        RefuelingForm form = new RefuelingForm()
                .setId(id)
                .setUserId(userId)
                .setCarId(carId)
                .setKilometers(kilometers)
                .setLiters(liters)
                .setCost(cost)
                .setComment(comment);

        Refueling model = refuelingService.toModel(form);

        assertEquals(id, model.getId());
        assertEquals(liters, model.getLiters());
        assertEquals(comment, model.getComment());

        assertNotNull(model.getUser());
        assertEquals(userId, model.getUser().getId());

        assertNotNull(model.getCar());
        assertEquals(carId, model.getCar().getId());
    }

    @Test
    public void testFromModel_WithNull() {
        RefuelingForm refuelingForm = refuelingService.fromModel(null);
        assertNull(refuelingForm);
    }

    @Test
    public void testFromModel() {
        final Refueling model = new Refueling()
                .setId(id)
                .setUser(mockUser)
                .setCar(mockCar)
                .setLiters(liters)
                .setComment(comment);
        final RefuelingForm form = refuelingService.fromModel(model);

        assertEquals(id, form.getId());
        assertEquals(userId, form.getUserId());
        assertEquals(carId, form.getCarId());
        assertEquals(liters, form.getLiters());
        assertEquals(comment, form.getComment());
    }
}