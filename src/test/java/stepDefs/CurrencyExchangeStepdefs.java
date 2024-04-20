package stepDefs;

import common.ApiRequestTypeConstant;
import common.FileReader;
import common.ReadYmlFile;
import common.RestAssuredUtils;
import commons.Endpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ResponseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CurrencyExchangeStepdefs {

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeStepdefs.class);
    FileReader fileReader = new ReadYmlFile();
    Map<String, Object> envData;
    {
        try {
            envData = fileReader.readFile("src/test/resources/config/rateexchangeConfig.yml");
        } catch (FileNotFoundException e) {
            logger.error("File is not present at the file path" + e.getMessage());
        }
    }
    ResponseOptions responseValue;
    RestAssuredUtils restUtilToken;
    String exhangeUrl = envData.get("URI").toString();

    @When("print hello world")
    public void print_hello_world() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Hello World");
    }

    @When("User send  GET request to exchange rate API")
    public ResponseOptions user_send_get_request_to_exchange_rate_api() {
        restUtilToken = new RestAssuredUtils(exhangeUrl, Endpoints.usdExhangePath,
                ApiRequestTypeConstant.GET.toString());
        responseValue = restUtilToken.executeGetapi();
        return responseValue;
    }

    @Then("User receive the status code {int}")
    public void user_receive_the_status_code(Integer status) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(user_send_get_request_to_exchange_rate_api().getStatusCode(), equalTo(status));
    }

    @Then("User validate the response body {string}")
    public void user_validate_the_response_body(String result) {
        assertThat(user_send_get_request_to_exchange_rate_api().getBody().jsonPath().get("result"), equalTo(result));
    }

    @Then("Validate {int} currency pairs are retuned by the API")
    public void validate_currency_pairs_are_retuned_by_the_api(int currencyPairs) {
        System.out.println(user_send_get_request_to_exchange_rate_api().getBody().jsonPath().getMap("rates"));
        assertThat(user_send_get_request_to_exchange_rate_api().getBody().jsonPath().getMap("rates").size(), equalTo(currencyPairs));
    }

    @And("User validate the response body schema")
    public void userValidateTheResponseBodySchema() throws IOException {
        RestAssured.given().get(exhangeUrl+Endpoints.usdExhangePath).then().body(JsonSchemaValidator.matchesJsonSchema(new File(System.getProperty("user.dir")+"/src/test/resources/config/exchangeRateJosnSchema.json"))).extract().response();
    }

    @And("Validate USD price against the {string} and make sure the prices are in range on {string} – {string} and validate the {string}")
    public void validateUSDPriceAgainstTheAndMakeSureThePricesAreInRangeOnAndValidateThe(DataTable table) {
        Map<String, String> data = table.asMap(String.class, String.class);
        double rate1Price = Double.parseDouble(user_send_get_request_to_exchange_rate_api().getBody().jsonPath().get("rates." + data.get("rate1")).toString());
        assertThat(rate1Price>Double.parseDouble(data.get("priceRangeLowerSide")) && rate1Price<Double.parseDouble(data.get("priceRangeUpperSide")), equalTo(Boolean.parseBoolean(data.get("status"))));
    }

    @Then("I validate the USD price against the following currencies:")
        public void validateUSDPrice(DataTable dataTable) {
            List<Map<String, String>> currencies = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> currency : currencies) {
                String currencyCode = currency.get("Currency");
                double rate1Price = Double.parseDouble(user_send_get_request_to_exchange_rate_api().getBody().jsonPath().get("rates." + currencyCode).toString());
                double lowerRange = Double.parseDouble(currency.get("PriceRangeLowerSide"));
                double upperRange = Double.parseDouble(currency.get("PriceRangeUpperSide"));
                boolean expectedStatus = Boolean.parseBoolean(currency.get("Status"));
                assertThat(rate1Price>lowerRange && rate1Price<upperRange, equalTo(expectedStatus));// Perform validation for each currency
            }
        }

}
