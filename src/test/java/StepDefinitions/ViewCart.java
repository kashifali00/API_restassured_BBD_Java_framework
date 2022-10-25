package StepDefinitions;

import Constants.Endpoints;
import Pojos.Item;
import Pojos.PojoViewCart;
import ServiceHelper.RestServiceLibrary;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ViewCart {
    RestServiceLibrary serviceLibrary = new RestServiceLibrary();
    private static Logger logger = LogManager.getLogger(ViewCart.class);
    JsonElement jsonElement;
    String response;

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode;
    PojoViewCart pojoViewCart;
    Item item = new Item();


    @Given("User is on demoblaze")
    public void user_is_on_demoblaze() {
        logger.info("Executing viewCart Given statement");

    }
    @When("User hit endpoint viewcart")
    public void user_hit_endpoint_viewcart() throws IOException {

        item = objectMapper.readValue(serviceLibrary.getJsonAsString("src/test/resources/JsonData/viewCart.json"), Item.class);
        logger.info("Executing viewCart When block");
        response = serviceLibrary.httpPostMethod(item, Endpoints.VIEW_CART);
        //jsonNode = objectMapper.readTree(response);
        //logger.info(response);
        pojoViewCart = objectMapper.readValue(response, PojoViewCart.class);


    }
    @Then("Response status should be {int}")
    public void response_status_should_be_ok(Integer int1) {
        System.out.println("pojoviewcart" + pojoViewCart.getItems().get(0).getCookie().toString());

        //System.out.println("JsonNode : " + jsonNode.get("Items").get(0).get("cookie").asText());

        // GSON library for deserialization
//        jsonElement = new Gson().fromJson(response, JsonElement.class);
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//        JsonArray jsonArray = jsonObject.getAsJsonArray("Items");
//
//        //Assert the cookie value
//        Assert.assertEquals(jsonArray.get(0).getAsJsonObject().get("cookie").getAsString(), "user=8672d7fe-6d1f-c700-07ca-202985bdd8ca");

    }
}
