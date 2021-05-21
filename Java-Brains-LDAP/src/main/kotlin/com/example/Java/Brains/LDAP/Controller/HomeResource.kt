package com.example.Java.Brains.LDAP.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HomeResource {

    @GetMapping("/")
    fun index():String{
        return "Home Page";
    }

}