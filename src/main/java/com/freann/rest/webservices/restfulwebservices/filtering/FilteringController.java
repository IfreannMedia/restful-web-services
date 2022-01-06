package com.freann.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean bean = new SomeBean("Value 1", "Value 2", "Sensitive Value 3");

        return getMappingJacksonValue(bean, "SomeBeanFilter", SimpleBeanPropertyFilter
                .filterOutAllExcept("field1", "field2"));
    }

    private MappingJacksonValue getMappingJacksonValue(Object bean, String filterId, SimpleBeanPropertyFilter propFilter) {
        MappingJacksonValue mapping = new MappingJacksonValue(bean);
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterId, propFilter);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/filtering/all")
    public MappingJacksonValue retrieveListOfSomeBean() {
        List<SomeBean> someBeans = Arrays.asList(
                new SomeBean("Value 1", "Value 2", "Sensitive Value 3"),
                new SomeBean("Value Unos", "Value Dos", "Sensitive Value Tres"),
                new SomeBean("Value Eins", "Value Zwei", "Sensitive Value Drei")
        );

        return getMappingJacksonValue(someBeans, "SomeBeanFilter", SimpleBeanPropertyFilter
                .filterOutAllExcept("field3"));
    }
}
