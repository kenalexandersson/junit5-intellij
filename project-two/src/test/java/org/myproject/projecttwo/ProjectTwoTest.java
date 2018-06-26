package org.myproject.projecttwo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectTwoTest {

    @Test
    void firstTestOfProjectTwo() {
        assertEquals("aName", ProjectTwoUtil.aName());
    }
}
