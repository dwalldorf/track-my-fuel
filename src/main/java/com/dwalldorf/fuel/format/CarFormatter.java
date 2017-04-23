package com.dwalldorf.fuel.format;

import com.dwalldorf.fuel.model.Car;
import org.springframework.util.StringUtils;

public class CarFormatter {

    public static String carDisplayName(final Car car) {
        String name = "";
        if (car.getManufacturer() != null) {
            name += car.getManufacturer();
        }
        if (!StringUtils.isEmpty(car.getModelName())) {
            name += " " + car.getModelName();
        }

        return name;
    }
}