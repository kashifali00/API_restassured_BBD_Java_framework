package ServiceHelper;


import Utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.filters;

public class RestServiceLibrary {

    public static final String BASE_URI = ConfigManager.getConfigManagerInstance().getKeyValue("baseURL");
    //public static final String PORT = ConfigManager.getConfigManagerInstance().getKeyValue("port");
    private static Logger logger = LogManager.getLogger(RestServiceLibrary.class);
    JsonPath jsonPath;
    ResponseBody body;
    String responseBody;


    public RestServiceLibrary(){
        logger.info("RestServiceLibraryConstructor :: Setting up BaseURI & Port");
        RestAssured.baseURI = BASE_URI;
        logger.info("BASE_URI :[" + RestAssured.baseURI + "]");
        //RestAssured.port = Integer.parseInt(PORT);
      //  logger.info("PORT :[" + RestAssured.port + "]");
        RestAssured.useRelaxedHTTPSValidation();
        filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    public String httpPostWithBasicAuthentication(Object map, String endpoint, String username, String password){

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .basic(username, password)
                .body(map)
                .when()
                .post(endpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());

        logger.info("httpGetWithBasicAuthentication EXECUTED SUCCESSFULLY>");
        Assert.assertEquals(response.getStatusCode(), 200);
        return response.asString();

    }

    // HTTP GET METHOD WITHOUT HEADER, PATH PARAMETER, COOKIE & QUERY PARAMETER
    public Response httpGetMethodWithoutHeaderAndParam(String serviceEndpoint){
        logger.info("<Executing httpGetMethodWithoutHeaderAndParam> ");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(serviceEndpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());

        logger.info("<httpGetMethodWithoutHeaderAndParam EXECUTED SUCCESSFULLY>");
        return response;
    }

    // HTTP GET METHOD WITH SINGLE HEADER
    public Response httpGetMethodWithHeaderWithoutParam(Header header, String serviceEndpoint){
        logger.info("<Executing httpGetMethodWithHeaderWithoutParam>");
        Response response = RestAssured.given()
                .header(header)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(serviceEndpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());

        logger.info("<httpGetMethodWithHeaderWithoutParam EXECUTED SUCCESSFULLY>");
        return response;
    }

    // HTTP GET METHOD WITH MULTIPLE HEADERS
    public Response httpGetMethodWithHeadersWithoutParam(HashMap headers, String serviceEndpoint){
        logger.info("Executing <httpGetMethodWithHeadersWithoutParam>");
        Response response = RestAssured.given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(serviceEndpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());

        logger.info("<httpGetMethodWithHeadersWithoutParam EXECUTED SUCCESSFULLY>");
        return response;
    }

    // HTTP GET METHOD WITH PATH PARAMETERS
    public Response httpGetMethodWithPathParams(HashMap pathParams, String serviceEndpoint){
        logger.info("<Executing httpGetMethodWithPathParams Method> ");
        Response response = RestAssured.given()
                .pathParams(pathParams)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(serviceEndpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());

        if(response != null) {
            logger.info("<httpGetMethodWithPathParams Method Executed successfully>");
        }
        return response;
    }

    // HTTP GET METHOD WITH PATH PARAMETERS
    public Response httpGetMethodWithPathParamsAndHeaders(HashMap pathParams, HashMap headers, String serviceEndpoint){
        logger.info("<Executing httpGetMethodWithPathParams Method> ");
        Response response = RestAssured.given()
                .pathParams(pathParams)
                .headers(headers)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(serviceEndpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());

        if(response != null) {
            logger.info("<httpGetMethodWithPathParams Method Executed successfully>");
        }
        return response;
    }

    // HTTP GET METHOD WITH QUERY PARAMETER
    public Response httpGetMethodWithQueryParams(HashMap map, String serviceEndpoint){
        logger.info("<Executing httpGetMethodWithQueryParams> ");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParams(map)
                .when()
                .get(serviceEndpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());

        logger.info("<httpGetMethodWithQueryParams EXECUTED SUCCESSFULLY>");
        return response;
    }

    // HTTP POST METHOD WITH QUERY PARAMETER
    public Response httpPostMethodWithQueryParams(HashMap map, String serviceEndpoint){
        logger.info("<Executing httpPostMethodWithQueryParams> ");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParams(map)
                .when()
                .get(serviceEndpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());

        logger.info("<httpGetMethodWithQueryParams EXECUTED SUCCESSFULLY>");
        return response;
    }

    // HTTP GET METHOD WITH QUERY PARAMETER & HEADERS
    public Response httpGetMethodWithQueryParamsAndHeaders(HashMap queryParams, HashMap headers, String serviceEndpoint){
        logger.info("<Executing httpGetMethodWithQueryParams> ");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .get(serviceEndpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());

        logger.info("<httpGetMethodWithQueryParams EXECUTED SUCCESSFULLY>");
        return response;
    }

    // HTTP POST METHOD WITH QUERY PARAMS
    public String httpPostMethod(Object object, String serviceEndpoint){
        logger.info("<Executing httpPostMethod> ");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(object)
                .post(serviceEndpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("<httpPostMethod EXECUTED SUCCESSFULLY>");
        return response.asString();

    }

    public Response httpDeleteMethod(HashMap map, String username, String password, String endpoint){
        logger.info("<Executing httpDeleteMethod> ");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .basic(username,password)
                .queryParams(map)
                .when()
                .delete(endpoint)
                .andReturn();
        logger.info("Response :: " + response.asString());
        logger.info("<httpDeleteMethod EXECUTED SUCCESSFULLY>");

        return response;

    }

    public JsonPath getJsonPath(Response response){
        jsonPath = response.jsonPath();
        return jsonPath;

    }

    public String getResponseAsString(Response response){
        body = response.getBody();
        responseBody = body.asString();
        return responseBody;
    }




}
