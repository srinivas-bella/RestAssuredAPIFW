package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Util {
	public static RequestSpecification reqsb;
	ResponseSpecification respsb;

	
	public RequestSpecification requestSpec() throws IOException
	{
		if(reqsb==null)
		{
		
	    	PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			reqsb = new RequestSpecBuilder().setBaseUri(getGlobalvalues("baseUrl")).addQueryParam("key", "qaclick123")
	    			.addFilter(RequestLoggingFilter.logRequestTo(log)).setContentType(ContentType.JSON)
	    			.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
	    	return reqsb;
		}
		
		return reqsb;

	}
	
//	public ResponseSpecification responseSpec()
//	{
//    	respsb = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//    	return respsb;
//
//	}
	
	public String getGlobalvalues(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getValueFromJson(Response response, String key)
	{
		String resp = response.asString();
		JsonPath jp = new JsonPath(resp);
		return jp.getString(key);
		
		
	}

}
