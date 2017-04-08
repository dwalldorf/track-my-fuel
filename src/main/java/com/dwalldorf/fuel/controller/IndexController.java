package com.dwalldorf.fuel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    public static final String ROUTE_PAGE_INDEX = "/";

    private static final String VIEW_INDEX = "/index";

    @GetMapping(ROUTE_PAGE_INDEX)
    public String index() {
        return VIEW_INDEX;
    }
}