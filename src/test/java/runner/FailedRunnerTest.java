package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        plugin = {"pretty","html:target/reports/currencyExchange-report.html"}
        ,features = "@target/rerun.txt"
        ,glue = {"stepDefs"}
        ,monochrome = true
        ,publish = true)

@RunWith(Cucumber.class)
public class FailedRunnerTest {
}
