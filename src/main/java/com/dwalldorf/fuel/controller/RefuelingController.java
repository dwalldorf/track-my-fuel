package com.dwalldorf.fuel.controller;

import com.dwalldorf.fuel.exception.NotFoundException;
import com.dwalldorf.fuel.form.refueling.RefuelingForm;
import com.dwalldorf.fuel.model.Refueling;
import com.dwalldorf.fuel.service.RefuelingService;
import com.dwalldorf.fuel.service.UserService;
import com.dwalldorf.fuel.util.RouteUtil;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RefuelingController {

    private static final String ROUTE_PREFIX = "/refueling";
    private static final String ROUTE_PAGE_LIST = ROUTE_PREFIX + "/list";
    private static final String ROUTE_PAGE_ADD = ROUTE_PREFIX + "/add";
    private static final String ROUTE_PAGE_EDIT = ROUTE_PREFIX + "/{id}/edit";
    private static final String ROUTE_ACTION_DELETE = ROUTE_PREFIX + "/{id}/delete";

    private static final String VIEW_PREFIX = "/refueling/";
    private static final String VIEW_LIST = VIEW_PREFIX + "list";
    private static final String VIEW_ADD = VIEW_PREFIX + "edit";

    private final RefuelingService refuelingService;
    private final UserService userService;

    @Inject
    public RefuelingController(RefuelingService refuelingService, UserService userService) {
        this.refuelingService = refuelingService;
        this.userService = userService;
    }

    @ModelAttribute("refuelings")
    public List<Refueling> refuelings() {
        return refuelingService.findAllByUser(userService.getCurrentUserId());
    }

    @GetMapping(ROUTE_PREFIX)
    public String indexRedirect() {
        return RouteUtil.redirectString(ROUTE_PAGE_LIST);
    }

    @GetMapping(ROUTE_PAGE_LIST)
    public String listPage() {
        return VIEW_LIST;
    }

    @GetMapping(ROUTE_PAGE_ADD)
    public ModelAndView addPage() {
        RefuelingForm refuelingForm = new RefuelingForm()
                .setDate(new DateTime());

        ModelAndView mav = new ModelAndView(VIEW_ADD);
        mav.addObject("refuelingForm", refuelingForm);
        return mav;
    }

    @GetMapping(ROUTE_PAGE_EDIT)
    public ModelAndView editPage(@PathVariable String id) {
        Refueling refueling = refuelingService.findById(id);
        if (refueling == null) {
            throw new NotFoundException();
        }

        userService.verifyOwner(refueling);

        ModelAndView mav = new ModelAndView(VIEW_ADD);
        mav.addObject("refuelingForm", new RefuelingForm().fromModel(refueling));
        return mav;
    }

    @PostMapping(ROUTE_PREFIX)
    public String saveAction(@ModelAttribute @Valid RefuelingForm refuelingForm) {
        Refueling refueling = refuelingForm.toModel();

        if (refuelingForm.getId() != null) {
            Refueling persistedRefueling = refuelingService.findById(refuelingForm.getId());
            if (persistedRefueling == null) {
                throw new NotFoundException();
            }
            userService.verifyOwner(persistedRefueling);
        } else {
            refueling.setUserId(userService.getCurrentUserId());
        }

        refuelingService.save(refueling);
        return RouteUtil.redirectString(ROUTE_PREFIX);
    }

    @DeleteMapping(ROUTE_ACTION_DELETE)
    public String deleteAction(@PathVariable String id) {
        Refueling refueling = refuelingService.findById(id);

        if (refueling == null) {
            throw new NotFoundException();
        }
        userService.verifyOwner(refueling);
        refuelingService.delete(refueling);

        return RouteUtil.redirectString(ROUTE_PREFIX);
    }
}
