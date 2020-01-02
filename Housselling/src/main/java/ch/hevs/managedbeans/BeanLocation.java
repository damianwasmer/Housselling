package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Location;
import ch.hevs.businessobject.Owner;
import ch.hevs.housesellingservice.Houseselling;

public class BeanLocation {

	private Houseselling houseselling;
	private String city;
    private String location;
    private String postcode;
    private List<Location> locations;
    private List<String> locationNames;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to houseselling EJB
    	InitialContext ctx = new InitialContext();
		houseselling = (Houseselling) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/HousesellingBean!ch.hevs.housesellingservice.Houseselling");
			
    	// get owners
		locations = houseselling.getLocations();
		if(locations==null){
			locations = new ArrayList<>(); 
    	}
		this.locationNames = new ArrayList<String>();
		for (Location location : locations) {
			this.locationNames.add(location.getCity());
		}
    }
    
    
        
	public List<String> getLocationNames() {
		return locationNames;
	}



	public void setLocationNames(List<String> locationNames) {
		this.locationNames = locationNames;
	}



	public Houseselling getHouseselling() {
		return houseselling;
	}



	public void setHouseselling(Houseselling houseselling) {
		this.houseselling = houseselling;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}



	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}



	public List<Location> getLocations() {
		return locations;
	}



	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}



	public void addLocation() {
    	houseselling.addLocation(location, postcode);
    }
	
	public void deleteLocation(Location location){
		houseselling.deleteLocation(location);
		locations.remove(location);
	}

}