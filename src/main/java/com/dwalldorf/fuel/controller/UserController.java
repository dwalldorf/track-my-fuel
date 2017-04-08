package com.dwalldorf.fuel.controller;

import com.dwalldorf.fuel.exception.InvalidFormInputException;
import com.dwalldorf.fuel.form.user.RegisterForm;
import com.dwalldorf.fuel.form.user.UserForm;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.service.UserService;
import com.dwalldorf.fuel.util.RouteUtil;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private static final String ROUTE_PAGE_REGISTER = "/register";
    private static final String ROUTE_ACTION_REGISTER = "/register";
    private static final String ROUTE_PAGE_EDIT = "/user/edit";
    private static final String ROUTE_ACTION_EDIT = "/user/edit";

    private final static String VIEW_PREFIX = "/user/";
    private final static String VIEW_REGISTER = VIEW_PREFIX + "register";
    private final static String VIEW_EDIT = VIEW_PREFIX + "edit";

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ROUTE_PAGE_REGISTER)
    public String registerPage(@ModelAttribute RegisterForm registerForm) {
        return VIEW_REGISTER;
    }

    @PostMapping(ROUTE_ACTION_REGISTER)
    public String register(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return registerPage(registerForm);
        }

        try {
            userService.register(registerForm);
        } catch (InvalidFormInputException e) {
            ObjectError objectError = e.toFormError(bindingResult.getObjectName());
            bindingResult.addError(objectError);

            return registerPage(registerForm);
        }

        return RouteUtil.redirectString("/login");
    }

    @GetMapping(ROUTE_PAGE_EDIT)
    public ModelAndView editPage() {
        ModelAndView mav = new ModelAndView(VIEW_EDIT);

        User user = userService.getCurrentUser();
        mav.addObject("userForm", UserForm.fromEntry(user));

        return mav;
    }

    @PostMapping(ROUTE_ACTION_EDIT)
    public String update(@ModelAttribute @Valid UserForm userForm) {
        userService.verifyOwner(userForm);

        User user = userService.getCurrentUser();
        user.setUsername(userForm.getUsername())
            .setEmail(userForm.getEmail())
            .setDefaultCustomerId(user.getDefaultCustomerId());

        userService.save(user);

        return RouteUtil.redirectString(IndexController.ROUTE_PAGE_INDEX);
    }
}