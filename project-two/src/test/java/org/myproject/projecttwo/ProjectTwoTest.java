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

    @Test
    void secondTestOfProjectTwo() throws URISyntaxException, IOException {
        String expectedData = "Some text in fileTest.txt";

        String fileName = "fileTest.txt";
        URL resourceUrl = getClass().getClassLoader().getResource(fileName);

        if (resourceUrl != null) {
            Path path = Paths.get(resourceUrl.toURI());

            StringBuilder data = new StringBuilder();
            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> data.append(line).append("\n"));
            lines.close();

            assertEquals(expectedData, data.toString().trim());

        } else {
            fail(String.format("Failed to handle file %s (couldn't be found on classpath)", fileName));
        }
    }
}
