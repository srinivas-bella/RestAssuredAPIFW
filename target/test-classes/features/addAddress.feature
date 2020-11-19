Feature: Check AddAddress API
  I want ti valdate the API for adding the address
  
	@AddPlace
  Scenario Outline: Add Address API validation
    Given I had Add Address input payload with "<Latitude>" "<Longitude>" "<Accuracy>" "<Name>" "<Ph Number>" "<Address>" "<Types>" "<Website>" "<Language>"
    When I call the "addAddressAPI" with "POST" http request
    Then Status call is done successfully with status code 200
    And "status" in output payload is "OK"
    And "scope" in output payload is "APP"
    And verify place_id created maps to "<Name>" using "getAddressAPI"
    
  Examples: 
  	|Latitude		|Longitude		|Accuracy		|Name										|Ph Number					|Address							|Types														|Website				|Language		|
  	|50.61284		|-67.3345698	|62.87			|White House New4				|(+88) 777 999 6767	|202, Pensilvenia USA	|USA, Security										|www.usa.us			|US-English	|
  	|10.2325		|-2.64398			|100				|British Parliament New	|(+44) 999 888 4242	|999, London UK				|UK, Security, Tourist Attraction	|www.london.uk	|UK-English	|
  	|32.363			|-47.86283		|69					|Indian Parliament New	|(+91) 911 108 5759	|22, Delhi INDIA			|INDIA, Sabha, Tourist Attraction	|www.delh.in		|IN-Hindi		|

	@DeletePlace
	Scenario: Delete Address API validation
	Given I had Delete Address payload
	When I call the "deleteAddressAPI" with "POST" http request
	Then Status call is done successfully with status code 200
	And "status" in output payload is "OK"
	And Get call should fail with status code 404