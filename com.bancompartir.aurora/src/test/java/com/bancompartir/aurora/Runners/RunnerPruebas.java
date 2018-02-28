package com.bancompartir.aurora.Runners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/", tags = "@pruebasCHC", glue = "com.bancompartir.aurora.Definitions")
public class RunnerPruebas {
}