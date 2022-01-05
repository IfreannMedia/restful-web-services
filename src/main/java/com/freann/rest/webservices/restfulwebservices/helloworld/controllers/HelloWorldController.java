package com.freann.rest.webservices.restfulwebservices.helloworld.controllers;

import com.freann.rest.webservices.restfulwebservices.helloworld.beans.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "hello world";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("hello-world");
    }

    @GetMapping(path = "/hello-world/path-var/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("hello-world, %s", name));
    }

    @GetMapping(path = "/hello-world-i18n")
    public String helloWorldInternationalised(@RequestHeader(name = "accept-language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }
}
