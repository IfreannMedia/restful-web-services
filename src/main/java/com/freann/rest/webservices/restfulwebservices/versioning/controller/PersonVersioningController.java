package com.freann.rest.webservices.restfulwebservices.versioning.controller;

import com.freann.rest.webservices.restfulwebservices.versioning.Name;
import com.freann.rest.webservices.restfulwebservices.versioning.PersonV1;
import com.freann.rest.webservices.restfulwebservices.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {


    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Richard Smith");
    }
    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Becky", "Costello"));
    }

}
