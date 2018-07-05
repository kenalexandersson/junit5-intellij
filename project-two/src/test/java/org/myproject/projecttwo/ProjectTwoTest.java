package org.myproject.projecttwo;

import org.junit.jupiter.api.Test;
import org.myproject.testperson.TestPerson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ProjectTwoTest {

    @Test
    void firstTestOfProjectTwo() {
        String firstName = "ProjectTwoPerson";
        TestPerson testPerson = TestPerson.of(firstName);
        assertEquals(firstName, testPerson.getFirstName());
    }
}
