package org.myproject.projectone;

import org.junit.jupiter.api.Test;
import org.myproject.projecttwo.ProjectTwoUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectOneTest {

    @Test
    void firstTestOfProjectOne() {
        assertEquals("aName", ProjectTwoUtil.aName());
    }
}
