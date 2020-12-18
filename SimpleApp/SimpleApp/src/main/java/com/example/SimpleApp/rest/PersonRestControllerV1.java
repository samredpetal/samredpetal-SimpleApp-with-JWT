package com.example.SimpleApp.rest;


import com.example.SimpleApp.model.Person;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonRestControllerV1 {
    private List<Person> PERSONS = Stream.of(
            new Person(1L, "Sam"),
            new Person(2L, "Anna"),
            new Person(3L, "Lucy")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Person> getAll() {
        return PERSONS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:read')")
    public Person getById(@PathVariable Long id) {
        return PERSONS.stream().filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('persons:write')")
    public Person create(@RequestBody Person person) {
        this.PERSONS.add(person);
        return person;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:write')")
    public void deleteById(@PathVariable Long id) {
        this.PERSONS.removeIf(developer -> developer.getId().equals(id));
    }


}
