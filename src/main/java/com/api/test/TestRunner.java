package com.api.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"StepDefinitions"},
        tags = "@AddItem or @ViewCart or @DeleteItem",
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json"})

public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void main (){

        System.out.println("running before class");

    }


}
