package com.soap.api.pokesoap;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/java/com/soap/api/pokesoap/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.soap.api.pokesoap.cucumberglue")
@ConfigurationParameter(key = Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
//@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@negative")
public class CucumberSuite {
}