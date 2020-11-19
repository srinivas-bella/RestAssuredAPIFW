package resources;

import java.util.ArrayList;
import java.util.List;

import pojoClasses.Location;
import pojoClasses.addAddresspayload;

public class TestDataBuilder {
	
	addAddresspayload apl = new addAddresspayload();

	
	public addAddresspayload addPlacePayload(double latitude, double longitude, String accuracy, String name, String phNumber, String address, String types, String website, String language) 
	{
		Location loc = new Location();
		loc.setLat(latitude);
		loc.setLng(longitude);
		apl.setLocation(loc);
		apl.setAccuracy(accuracy);
		apl.setName(name);
		apl.setPhone_number(phNumber);
		apl.setAddress(address);
		String[] lst = types.split(", ");
		lst = types.split(", ");
		apl.setTypes(lst);
		apl.setWebsite(website);
		apl.setLanguage(language);
		return apl;
	}
	
	public String deletePlacePayload(String placeID)
	{
		return "{ \n"
				+ "    \"place_id\":\""+placeID+"\" \n"
				+ "} ";
	}

}
