package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/reports/currencyExchange-report.html",
                "rerun:target/rerun.txt"}
        ,features= {"src/test/resources/featureFiles"}
        ,glue = {"stepDefs"}
        ,monochrome = true
        ,snippets = SnippetType.CAMELCASE
        ,tags = "@Currency"
        ,publish = true
)
public class CurrencyExchangeRunnerTest {
}
