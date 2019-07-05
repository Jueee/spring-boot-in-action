package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ManController {

    @Autowired
    private ManService manService;

    @GetMapping("/ok")
    String home() {
        Man man = manService.getInfoByName("Jueee")
        return "ok ==> groovy "+"name:"+man.name
    }
}