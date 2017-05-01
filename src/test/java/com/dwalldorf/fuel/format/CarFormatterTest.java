package com.dwalldorf.fuel.format;

import static org.junit.Assert.assertEquals;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.model.Car;
import org.junit.Test;

public class CarFormatterTest extends BaseTest {

    private static final String manufacturer = "Subaru";
    private static final String modelName = "Levorg";

    @Test
    public void testCarDisplayName_Null() {
        final String name = CarFormatter.carDisplayName(null);
        assertEquals("", name);
    }

    @Test
    public void testCarDisplayName_OnlyManufacturer() {
        final Car car = new Car().setManufacturer(manufacturer);
        final String name = CarFormatter.carDisplayName(car);

        assertEquals(manufacturer, name);
    }

    @Test
    public void testCarDisplayName_OnlyModelName() {
        final Car car = new Car().setModelName(modelName);
        final String name = CarFormatter.carDisplayName(car);

        assertEquals(modelName, name);
    }

    @Test
    public void testCarDisplayName() {
        final String expectedName = manufacturer + " " + modelName;
        final Car car = new Car()
                .setManufacturer(manufacturer)
                .setModelName(modelName);
        final String actualName = CarFormatter.carDisplayName(car);

        assertEquals(expectedName, actualName);
    }
}