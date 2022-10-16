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
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;

public class DeleteCart {

    RestServiceLibrary serviceLibrary = new RestServiceLibrary();
    private static Logger logger = LogManager.getLogger(DeleteCart.class);
    JsonElement jsonElement;
    String response;
    Response resp;
    String prod_id;
    String id;
    String username = "f817e22f1526b048799f75da";
    String passwd = "7641251982b983cfd92b5a25fa97cd3ee9e21920f21d8b14cd705831826935723f3033f0";

    @Given("User has already item in the cart")
    public void user_has_already_item_in_the_cart() {

        logger.info("Executing DeleteCart scenarios");
        HashMap<Object, Object> map = new HashMap();
        map.put("cookie", "user=8672d7fe-6d1f-c700-07ca-202985bdd8ca");
        map.put("flag", false);
        response = serviceLibrary.httpPostMethod(map, Endpoints.VIEW_CART);
        logger.info(response);

        // GSON library for deserialization
        jsonElement = new Gson().fromJson(response, JsonElement.class);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("Items");
        logger.info(jsonArray);

        //Getting last element of the list
        for(int iter=0; iter<jsonArray.size(); iter++){
            if(iter == jsonArray.size() -1 ){
                prod_id = jsonArray.get(iter).getAsJsonObject().get("prod_id").getAsString();
                id = jsonArray.get(iter).getAsJsonObject().get("id").getAsString();
            }

        }

    }
    @When("User hit endpoint deletecart")
    public void user_hit_endpoint_deletecart() {

        HashMap<Object, Object> map = new HashMap();
        map.put("prod_id", prod_id);

        resp = serviceLibrary.httpDeleteMethod(map,username, passwd, Endpoints.DELETE_CART);

    }
    @Then("Response should be {int}")
    public void response_should_be(Integer int1) {
        Assert.assertEquals(resp.getStatusCode(), 405);
    }
}
