package org.myproject.projectone;

import org.junit.jupiter.api.Test;
import org.myproject.testperson.TestPerson;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectOneTest {

    @Test
    void firstTestOfProjectOne() {
        String firstName = "ProjectOnePerson";
        TestPerson testPerson = TestPerson.of(firstName);
        assertEquals(firstName, testPerson.getFirstName());
    }
}
