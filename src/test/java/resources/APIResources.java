package resources;

public enum APIResources {
	
	addAddressAPI("/maps/api/place/add/json"),
	getAddressAPI("/maps/api/place/get/json"),
	updateAddressAPI("/maps/api/place/update/json"),
	deleteAddressAPI("/maps/api/place/delete/json");
	
	private String resource;

	APIResources(String resource) 
	{
		this.resource = resource;
		
	}
	
	public String getValue()
	{
		return resource;
	}

}
