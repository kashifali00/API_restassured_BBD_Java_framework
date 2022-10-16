package StepDefinitions;

import Constants.Endpoints;
import ServiceHelper.RestServiceLibrary;
import com.google.gson.JsonElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class AddItem {

    RestServiceLibrary serviceLibrary = new RestServiceLibrary();
    private static Logger logger = LogManager.getLogger(ViewCart.class);
    JsonElement jsonElement;
    String response;

    @Given("User is authorized")
    public void user_is_authorized() {

    }
    @When("User hit endpoint additem")
    public void user_hit_endpoint_additem() {

        logger.info("Executing AddItemToCart scenario");
        HashMap<Object, Object> map = new HashMap();
        String username = "f817e22f1526b048799f75da";
        String passwd = "7641251982b983cfd92b5a25fa97cd3ee9e21920f21d8b14cd705831826935723f3033f0";
        map.put("id", "c1e8cbd0-00fb-af01-cddb-a7d5f9680ced");
        map.put("cookie", "user=8672d7fe-6d1f-c700-07ca-202985bdd8ca");
        map.put("prod_id", 3);
        map.put("flag", false);

        response = serviceLibrary.httpPostWithBasicAuthentication(map,Endpoints.ADD_TO_CART,username,passwd);

    }
    @Then("Response should be OK and item should be added successfully")
    public void response_should_be_ok_and_item_should_be_added_successfully() {
        logger.info(response);


    }
}
