package com.freann.rest.webservices.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("Value 1", "Value 2", "Sensitive Value 3");
    }

    @GetMapping("/filtering/all")
    public List<SomeBean> retrieveListOfSomeBean() {
        return Arrays.asList(
                new SomeBean("Value 1", "Value 2", "Sensitive Value 3"),
                new SomeBean("Value Unos", "Value Dos", "Sensitive Value Tres"),
                new SomeBean("Value Eins", "Value Zwei", "Sensitive Value Drei")
        );
    }
}
