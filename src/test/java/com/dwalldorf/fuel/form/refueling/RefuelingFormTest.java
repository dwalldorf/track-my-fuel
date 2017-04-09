package com.dwalldorf.fuel.form.refueling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.model.Refueling;
import org.junit.Test;

public class RefuelingFormTest extends BaseTest {

    private static final String id = "123";
    private static final String userId = "321";
    private static final String comment = "comment";
    private static final Float liters = 33.3F,
            cost = 50.0F;
    private static final Long kilometers = 4000L;


    @Test
    public void testToModel() {
        Refueling model = new RefuelingForm()
                .setId(id)
                .setUserId(userId)
                .setKilometers(kilometers)
                .setLiters(liters)
                .setCost(cost)
                .setComment(comment)

                .toModel();

        assertEquals(id, model.getId());
        assertEquals(userId, model.getUserId());
        assertEquals(kilometers, model.getKilometers());
        assertEquals(liters, model.getLiters());
        assertEquals(cost, model.getCost());
        assertEquals(comment, model.getComment());
    }

    @Test
    public void testFromModel_WithNull() {
        RefuelingForm refuelingForm = new RefuelingForm().fromModel(null);
        assertNull(refuelingForm);
    }

    @Test
    public void testFromModel() {
        final Refueling model = new Refueling()
                .setId(id)
                .setUserId(userId)
                .setKilometers(kilometers)
                .setLiters(liters)
                .setCost(cost)
                .setComment(comment);
        final RefuelingForm form = new RefuelingForm().fromModel(model);

        assertEquals(id, form.getId());
        assertEquals(userId, form.getUserId());
        assertEquals(kilometers, form.getKilometers());
        assertEquals(liters, form.getLiters());
        assertEquals(cost, form.getCost());
        assertEquals(comment, form.getComment());
    }
}