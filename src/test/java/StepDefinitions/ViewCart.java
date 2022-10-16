package StepDefinitions;

import Constants.Endpoints;
import ServiceHelper.RestServiceLibrary;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;

public class ViewCart {
    RestServiceLibrary serviceLibrary = new RestServiceLibrary();
    private static Logger logger = LogManager.getLogger(ViewCart.class);
    JsonElement jsonElement;
    String response;


    @Given("User is on demoblaze")
    public void user_is_on_demoblaze() {
        logger.info("Executing viewCart Given statement");

    }
    @When("User hit endpoint viewcart")
    public void user_hit_endpoint_viewcart() {
        logger.info("Executing viewCart When block");
        HashMap<Object, Object> map = new HashMap();
        map.put("cookie", "user=8672d7fe-6d1f-c700-07ca-202985bdd8ca");
        map.put("flag", false);
        response = serviceLibrary.httpPostMethod(map, Endpoints.VIEW_CART);
        logger.info(response);


    }
    @Then("Response status should be {int}")
    public void response_status_should_be_ok(Integer int1) {

        // GSON library for deserialization
        jsonElement = new Gson().fromJson(response, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("Items");

        //Assert the cookie value
        Assert.assertEquals(jsonArray.get(0).getAsJsonObject().get("cookie").getAsString(), "user=8672d7fe-6d1f-c700-07ca-202985bdd8ca");

    }
}
