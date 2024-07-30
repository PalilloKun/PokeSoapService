package com.soap.api.pokesoap;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@SuppressWarnings("NewClassNamingConvention")
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:target/cucumber/bagextra"},
        glue = {"com.soap.api.pokesoap.cucumberglue"})
public class CucumberTestRunnerCase {
}

