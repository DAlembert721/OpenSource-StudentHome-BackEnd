package com.acme.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfiguration.class)
public class CucumberSpringConfiguration {
    @Before
    public void SetupCucumberSpringContext() {
        //dummy method so cucumber will recognize this class as glue
        //and use its context configuration
    }
}
