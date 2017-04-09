package com.dwalldorf.fuel.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.exception.NotFoundException;
import com.dwalldorf.fuel.form.refueling.RefuelingForm;
import com.dwalldorf.fuel.model.Refueling;
import com.dwalldorf.fuel.service.RefuelingService;
import com.dwalldorf.fuel.service.UserService;
import com.dwalldorf.fuel.util.RouteUtil;
import java.util.Map;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

public class RefuelingControllerTest extends BaseTest {

    @Mock
    private RefuelingService mockRefuelingService;

    @Mock
    private UserService mockUserService;

    private RefuelingController refuelingController;

    @Override
    protected void setUp() {
        this.refuelingController = new RefuelingController(mockRefuelingService, mockUserService);
    }

    @Test
    public void testIndexRedirect() {
        final String expectedRoute = RouteUtil.redirectString("/refueling/list");
        final String actualRoute = refuelingController.indexRedirect();

        assertEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testListPage_ViewName() {
        final String expectedViewName = "/refueling/list";
        final String actualViewName = refuelingController.listPage();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testAddPage_ViewName() {
        final String expectedViewName = "/refueling/edit";
        final String actualViewName = refuelingController.addPage().getViewName();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testAddPage_RefuelingFormModel() {
        final String expectedModelName = "refuelingForm";
        final Map<String, Object> modelMap = refuelingController.addPage().getModel();

        assertTrue(modelMap.containsKey(expectedModelName));

        final Object refuelingForm = modelMap.get(expectedModelName);
        assertTrue(refuelingForm instanceof RefuelingForm);
        assertNotNull(refuelingForm);
    }

    @Test(expected = NotFoundException.class)
    public void testEditPage_ThrowsNotFound() {
        final String id = "noSuchId";

        when(mockRefuelingService.findById(eq(id))).thenReturn(null);
        refuelingController.editPage(id);

        verify(mockRefuelingService).findById(eq(id));
    }

    @Test
    public void testEditPage_ViewName() {
        final String id = "123";
        when(mockRefuelingService.findById(eq(id))).thenReturn(new Refueling());

        final String expectedViewName = "/refueling/edit";
        final String actualViewName = refuelingController.editPage(id).getViewName();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testEditPage_RefuelingFormModel() {
        final String id = "123";
        when(mockRefuelingService.findById(eq(id))).thenReturn(new Refueling());

        final String expectedModelName = "refuelingForm";
        final Map<String, Object> modelMap = refuelingController.editPage(id).getModel();

        assertTrue(modelMap.containsKey(expectedModelName));

        final Object refuelingForm = modelMap.get(expectedModelName);
        assertTrue(refuelingForm instanceof RefuelingForm);
        assertNotNull(refuelingForm);
    }

    @Test(expected = NotFoundException.class)
    public void testSaveAction_ThrowsNotFound() {
        final String id = "noSuchId";
        when(mockRefuelingService.findById(eq(id))).thenReturn(null);

        RefuelingForm refuelingForm = new RefuelingForm().setId(id);
        refuelingController.saveAction(refuelingForm);
    }

    @Test
    public void testSaveAction_VerifiesOwner() {
        final String id = "123";
        final Refueling mockPersistedRefueling = new Refueling().setId(id);
        when(mockRefuelingService.findById(eq(id))).thenReturn(mockPersistedRefueling);

        refuelingController.saveAction(RefuelingForm.fromModel(mockPersistedRefueling));

        verify(mockUserService).verifyOwner(eq(mockPersistedRefueling));
    }

    @Test
    public void testSaveAction_NewEntry_SetsUserId() {
        final String mockUserId = "mockUserId";
        when(mockUserService.getCurrentUserId()).thenReturn(mockUserId);

        ArgumentCaptor<Refueling> refuelingCaptor = ArgumentCaptor.forClass(Refueling.class);
        refuelingController.saveAction(new RefuelingForm());

        verify(mockRefuelingService).save(refuelingCaptor.capture());
        final Refueling capturedRefueling = refuelingCaptor.getValue();

        assertNotNull(capturedRefueling.getUserId());
        assertEquals(mockUserId, capturedRefueling.getUserId());
    }

    @Test
    public void testSaveAction_Redirect() {
        final String expectedRedirect = RouteUtil.redirectString("/refueling");
        final String actualRedirect = refuelingController.saveAction(new RefuelingForm());

        assertEquals(expectedRedirect, actualRedirect);
    }
}