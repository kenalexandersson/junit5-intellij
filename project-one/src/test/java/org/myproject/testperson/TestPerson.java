package org.myproject.testperson;

public class TestPerson {

    private String firstName;

    public static TestPerson of(String firstName) {
        return new TestPerson(firstName);
    }

    private TestPerson(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
}