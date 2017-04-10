package com.dwalldorf.fuel.form.car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.model.Car;
import org.junit.Test;

public class CarFormTest extends BaseTest {

    private static final String id = "someId";
    private static final String userId = "userId";
    private static final String manufacturer = "Subaru";
    private static final String modelName = "Levorg";
    private static final String year = "2015";
    private static final String licensePlate = "B-dsaasd";

    @Test
    public void testToModel() {
        final Car model = new CarForm()
                .setId(id)
                .setUserId(userId)
                .setManufacturer(manufacturer)
                .setModelName(modelName)
                .setYear(year)
                .setLicensePlate(licensePlate)

                .toModel();

        assertEquals(id, model.getId());
        assertEquals(userId, model.getUserId());
        assertEquals(manufacturer, model.getManufacturer());
        assertEquals(modelName, model.getModelName());
        assertEquals(year, model.getYear());
        assertEquals(licensePlate, model.getLicensePlate());
    }

    @Test
    public void testFromModel_WithNull() {
        final CarForm form = new CarForm().fromModel(null);
        assertNull(form);
    }

    @Test
    public void testFromModel() {
        final Car model = new Car()
                .setId(id)
                .setUserId(userId)
                .setManufacturer(manufacturer)
                .setModelName(modelName)
                .setYear(year)
                .setLicensePlate(licensePlate);
        final CarForm form = new CarForm().fromModel(model);

        assertEquals(id, form.getId());
        assertEquals(userId, form.getUserId());
        assertEquals(manufacturer, form.getManufacturer());
        assertEquals(modelName, form.getModelName());
        assertEquals(year, form.getYear());
        assertEquals(licensePlate, form.getLicensePlate());
    }
}