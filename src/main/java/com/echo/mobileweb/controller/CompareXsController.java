package com.echo.mobileweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompareXsController {

    @GetMapping("compareXs")
    public String CompareXs(){
        return "compareXs";
    }

}
