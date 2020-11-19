package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void addPlaceFunctionality() throws IOException
	{
		addAddressSD aasd = new addAddressSD();

		if(addAddressSD.placeId==null) 
		{
			aasd.i_have_add_address_input_payload_with(11.223344, -66.223344, "55", "delTESTNAME12", "delTESTPHN12", "delTEST ADDRESS12", "delTYPE112, delTYPE222, delTYPE332", "www.delTEST12.com", "IN-delTEST12");
			aasd.i_call_the_api_with_post_http_request("addAddressAPI", "POST");
			aasd.verify_place_id_created_maps_to_using("delTESTNAME12", "getAddressAPI");
		}
		
	}

}
