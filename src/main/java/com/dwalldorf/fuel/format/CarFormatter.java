package com.dwalldorf.fuel.format;

import com.dwalldorf.fuel.model.Car;
import org.springframework.util.StringUtils;

public class CarFormatter {

    public static String carDisplayName(final Car car) {
        String name = "";
        if (car == null) {
            return name;
        }

        if (car.getManufacturer() != null) {
            name += car.getManufacturer();
        }
        if (!StringUtils.isEmpty(car.getModelName())) {
            if (!StringUtils.isEmpty(name)) {
                name += " ";
            }
            name += car.getModelName();
        }

        return name;
    }
}