package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{int1}/and/{int2}")
    @ResponseBody

    public Long addition(
            @PathVariable Long int1,
            @PathVariable Long int2
    ){
      return int1 + int2;
    }

    @GetMapping("/subtract/{int1}/from/{int2}")
    @ResponseBody

    public String subtract(
            @PathVariable Long int1,
            @PathVariable Long int2
    ){
        return "The difference is " + (int2 - int1);
    }

    @GetMapping("/multiple/{int1}/and/{int2}")
    @ResponseBody

    public Long multiply(
            @PathVariable Long int1,
            @PathVariable Long int2
    ){
        return int1 * int2;
    }

    @GetMapping("/divide/{int1}/by/{int2}")
    @ResponseBody

    public Long divide(
            @PathVariable Long int1,
            @PathVariable Long int2
    ){
        return int1 / int2;
    }
}
