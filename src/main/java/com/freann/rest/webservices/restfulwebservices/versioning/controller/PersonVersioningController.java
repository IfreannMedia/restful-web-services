package com.freann.rest.webservices.restfulwebservices.versioning.controller;

import com.freann.rest.webservices.restfulwebservices.versioning.Name;
import com.freann.rest.webservices.restfulwebservices.versioning.PersonV1;
import com.freann.rest.webservices.restfulwebservices.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {


    // URI versioning of API
    @GetMapping("v1/person")
    public PersonV1 personV1URI() {
        return new PersonV1("Richard Smith");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2URI() {
        return new PersonV2(new Name("Becky", "Costello"));
    }

    // param versioning of API
    @GetMapping(value = "person/param", params = "version=1")
    public PersonV1 personV1Param() {
        return new PersonV1("Richard Smith");
    }

    @GetMapping(value = "person/param", params = "version=2")
    public PersonV2 personV2Param() {
        return new PersonV2(new Name("Becky", "Costello"));
    }

    // Header versioning of API
    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 personV1Header() {
        return new PersonV1("Richard Smith");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 personV2Header() {
        return new PersonV2(new Name("Becky", "Costello"));
    }

    // Mime type/accept versioning of API
    @GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personV1Produces() {
        return new PersonV1("Richard Smith");
    }

    @GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personV2Produces() {
        return new PersonV2(new Name("Becky", "Costello"));
    }

}
