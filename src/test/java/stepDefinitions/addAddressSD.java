package stepDefinitions;

import org.junit.runner.Request;
import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.Location;
import pojoClasses.addAddresspayload;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;


//@RunWith(Cucumber.class)
public class addAddressSD extends Util{
	
	RequestSpecification request;
	static String placeId;
	Response response;
	TestDataBuilder tdb = new TestDataBuilder();
	APIResources resource;

    @Given("^I had Add Address input payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_have_add_address_input_payload_with(double latitude, double longitude, String accuracy, String name, String phNumber, String address, String types, String website, String language) throws IOException {
    	
    	request = given().spec(requestSpec()).body(tdb.addPlacePayload(latitude, longitude, accuracy, name, phNumber, address, types, website, language));
    	
    }

    @When("^I call the \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void i_call_the_api_with_post_http_request(String api,String reqMethod) {
    	
    	resource = APIResources.valueOf(api);
    	
    	if(reqMethod.equalsIgnoreCase("POST"))
    		response = request.when().post(resource.getValue());
    	else if(reqMethod.equalsIgnoreCase("GET"))
        	response = request.when().get(resource.getValue());
    	else if(reqMethod.equalsIgnoreCase("DELETE"))
        	response = request.when().delete(resource.getValue());
    	else if(reqMethod.equalsIgnoreCase("PUT"))
        	response = request.when().put(resource.getValue());

       }

    @Then("^Status call is done successfully with status code 200$")
    public void status_call_is_done_successfully_with_status_code_200()  {
    	assertEquals(200,response.getStatusCode());
    	
    }

    @And("^\"([^\"]*)\" in output payload is \"([^\"]*)\"$")
    public void something_in_output_payload_is_something(String strArg1, String strArg2)  {
    	
    	System.out.println(strArg2);
    	System.out.println(getValueFromJson(response, strArg1));
 
    	assertEquals(strArg2,getValueFromJson(response, strArg1));
    	
    	
    }
    
    @And("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verify_place_id_created_maps_to_using(String name, String api) throws IOException {
    	
    	placeId = getValueFromJson(response, "place_id");
    	System.out.println(placeId);
    	
    	resource = APIResources.valueOf(api);
    	
    	response = given().spec(requestSpec()).queryParam("place_id", placeId)
    	.when().get(resource.getValue());
    	
    	assertEquals(name,getValueFromJson(response, "name"));


    
    }
    
    @Given("^I had Delete Address payload$")
    public void i_had_delete_address_payload() throws IOException 
    {
    	request = given().spec(requestSpec()).body(tdb.deletePlacePayload(placeId));

    }
    
    @Then("^Get call should fail with status code 404$")
    public void get_call_should_fail_with_status_code() throws IOException {
    	request = given().spec(requestSpec()).queryParam("place_id", placeId);
    	
    	i_call_the_api_with_post_http_request("getAddressAPI","GET");
    	//resource = APIResources.valueOf("getAddressAPI");
    	response = request.when().get(resource.getValue());
    	assertEquals(404,response.getStatusCode());
    }
    
    

}
