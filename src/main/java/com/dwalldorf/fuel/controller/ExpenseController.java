package com.dwalldorf.fuel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpenseController {

    private static final String ROUTE_PREFIX = "/expenses";

    private static final String VIEW_PREFIX = "/expense/";
    private static final String VIEW_LIST = VIEW_PREFIX + "list";

    @GetMapping(ROUTE_PREFIX)
    public String indexAction() {
        return VIEW_LIST;
    }
}