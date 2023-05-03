package com.campusdual.appmazing.controller;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String testController(){
        return "Products controller works!";
    }

    @PostMapping
    public String testController(@RequestBody String name){
        return "Products controller works, " + name + "!";
    }
}
