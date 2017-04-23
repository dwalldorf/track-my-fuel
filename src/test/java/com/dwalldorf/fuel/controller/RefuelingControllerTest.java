package com.dwalldorf.fuel.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.dwalldorf.fuel.BaseTest;
import com.dwalldorf.fuel.exception.NotFoundException;
import com.dwalldorf.fuel.form.refueling.RefuelingForm;
import com.dwalldorf.fuel.model.Refueling;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.service.CarService;
import com.dwalldorf.fuel.service.ExpenseService;
import com.dwalldorf.fuel.service.RefuelingService;
import com.dwalldorf.fuel.service.UserService;
import com.dwalldorf.fuel.util.RouteUtil;
import java.util.Map;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

public class RefuelingControllerTest extends BaseTest {

    private static final Long id = 123L;
    private static final Long userId = 321L;

    private static final User mockUser = new User().setId(userId);

    @Mock
    private RefuelingService mockRefuelingService;

    @Mock
    private CarService mockCarService;

    @Mock
    private ExpenseService mockExpenseService;

    @Mock
    private UserService mockUserService;

    private RefuelingController refuelingController;

    @Override
    protected void setUp() {
        this.refuelingController = new RefuelingController(mockRefuelingService, mockCarService, mockExpenseService, mockUserService);
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
        when(mockRefuelingService.findById(eq(id))).thenReturn(null);
        refuelingController.editPage(id);

        verify(mockRefuelingService).findById(eq(id));
    }

    @Test
    public void testEditPage_ViewName() {
        when(mockRefuelingService.findById(eq(id))).thenReturn(new Refueling());

        final String expectedViewName = "/refueling/edit";
        final String actualViewName = refuelingController.editPage(id).getViewName();

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testEditPage_RefuelingFormModel() {
        Refueling refueling = new Refueling().setId(id);
        when(mockRefuelingService.findById(eq(id))).thenReturn(refueling);

        final String expectedModelName = "refuelingForm";
        final Map<String, Object> modelMap = refuelingController.editPage(id).getModel();

        assertTrue(modelMap.containsKey(expectedModelName));
        verify(mockRefuelingService).fromModel(eq(refueling));
    }

    @Test(expected = NotFoundException.class)
    public void testSaveAction_ThrowsNotFound() {
        when(mockRefuelingService.findById(eq(id))).thenReturn(null);

        RefuelingForm refuelingForm = new RefuelingForm().setId(id);
        refuelingController.saveAction(refuelingForm);
    }

    @Test
    public void testSaveAction_VerifiesOwner() {
        final Refueling mockPersistedRefueling = new Refueling().setId(id);
        final RefuelingForm mockPersistedRefuelingForm = new RefuelingForm().setId(id);
        when(mockRefuelingService.findById(eq(id))).thenReturn(mockPersistedRefueling);

        refuelingController.saveAction(mockPersistedRefuelingForm);

        verify(mockUserService).verifyOwner(eq(mockPersistedRefueling));
    }

    @Test
    public void testSaveAction_NewEntry_SetsUserId() {
        RefuelingForm refuelingForm = new RefuelingForm();

        when(mockUserService.getCurrentUser()).thenReturn(mockUser);
        when(mockRefuelingService.toModel(eq(refuelingForm))).thenReturn(new Refueling());

        ArgumentCaptor<Refueling> refuelingCaptor = ArgumentCaptor.forClass(Refueling.class);
        refuelingController.saveAction(refuelingForm);

        verify(mockRefuelingService).save(refuelingCaptor.capture());
        final Refueling capturedRefueling = refuelingCaptor.getValue();

        assertNotNull(capturedRefueling.getUser());
        assertEquals(userId, capturedRefueling.getUser().getId());
    }

    @Test
    public void testSaveAction_Redirect() {
        when(mockRefuelingService.toModel(any(RefuelingForm.class))).thenReturn(new Refueling());
        final String expectedRedirect = RouteUtil.redirectString("/refueling");
        final String actualRedirect = refuelingController.saveAction(new RefuelingForm());

        assertEquals(expectedRedirect, actualRedirect);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteAction_ThrowsNotFound() {
        when(mockRefuelingService.findById(eq(id))).thenReturn(null);

        refuelingController.deleteAction(id);
    }

    @Test
    public void testDeleteAction_VerifiesOwner() {
        Refueling mockPersistedRefueling = new Refueling();
        when(mockRefuelingService.findById(eq(id))).thenReturn(mockPersistedRefueling);

        refuelingController.deleteAction(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedRefueling));
    }

    @Test
    public void testDeleteAction_Deletion() {
        Refueling mockPersistedRefueling = new Refueling();
        when(mockRefuelingService.findById(eq(id))).thenReturn(mockPersistedRefueling);

        refuelingController.deleteAction(id);

        verify(mockRefuelingService).delete(eq(mockPersistedRefueling));
    }

    @Test
    public void testDeleteAction_Redirect() {
        when(mockRefuelingService.findById(eq(id))).thenReturn(new Refueling());

        final String expectedRedirect = RouteUtil.redirectString("/refueling");
        final String actualRedirect = refuelingController.deleteAction(id);

        assertEquals(expectedRedirect, actualRedirect);
    }
}