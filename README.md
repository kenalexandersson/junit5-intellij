# junit5-intellij

Project that showcases an anomaly in IntelliJ when executing junit5 tests under certain conditions.

## Summary

IntelliJ unexpectedly runs junit5 test classes from modules included by using maven test-jar dependencies.

Using the same setup in a junit4 based project, IntelliJ does not execute test classes from maven test-jar dependencies.

## Background

The code project consist of a Maven multi module project `junit5-intellij` having two child modules, `project-one` and `project-two`.
 
Module `project-one` contains tests but also a test utility class for generating a test object. The code is packaged into a jar using the goal _test-jar_ of `maven-jar-plugin`, which is defined in the pom.xml of project-one. 

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.6</version>
        <executions>
            <execution>
                <goals>
                    <goal>test-jar</goal>
                </goals>
            </execution>
        </executions>
    </plugin>

`project-two` is using the above test jar file as well, it gains access to it by adding the below dependency in the pom.xml of project-two.

    <dependency>
        <groupId>org.myproject</groupId>
        <artifactId>project-one</artifactId>
        <version>${project.version}</version>
        <type>test-jar</type>
        <scope>test</scope>
    </dependency>
    
`project-one` has one test class, `org.myproject.projectone.ProjectOneTest`, containing two tests, of which.

`project-two` has one test class, `org.myproject.projectywo.ProjectTwoTest`, containing a single test.
    
## Issue

When executing the tests of `project-one` in IntelliJ by selecting **project-one -> Run 'All Tests'**, the expected outcome is that the tests in class `ProjectOneTest` are run. 

The actual outcome is that the tests of both `ProjectOneTest` and `ProjectTwoTest` are executed.

Note that the default Run/Debug Configurations are used here, without modifications.

When running the same setup in Intellij using junit4, it works as expected, tests in `ProjectOneTest` are the only ones that are executed. The same goes if the test are executed via Maven surefire plugin.


**Result of project-one -> Run 'All Tests'**

![results of project1](doc/images/result-project-one.png)

When the tests of `project-one` are executed, both test cases are successful.
The test `ProjectOneTest.secondTestOfProjectOne()` processes a file that is read from classpath, originally located in `project-one/src/main/resources`.

**Result of project-two -> Run 'All Tests'**

![results of project2](doc/images/result-project-two.png)

When executing the tests of `project-two`, the tests of `ProjectOneTest` are detected and executed as well. 
In this case the test `ProjectOneTest.secondTestOfProjectOne()` will fail since it is executed using the classpath of `project-two`, and hence the file in `project-one/src/main/resources` will be missing.

## Workaround

One possible workaround is to edit the Run/Debug Configuration to explicitly point out the package to include tests from. 

Even though such a workaround will work, I think the best option would be if JetBrains could look into the matter and make IntelliJ keep the behavior from junit4 also when using the junit5 engine/platform/launcher. 

![workaround](doc/images/workaround.png)

