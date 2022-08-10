package com.jinternals.bufferlimit.domain;

import java.util.ArrayList;
import java.util.List;

public class PersonCollection {
    private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
