package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.House;
import ch.hevs.businessobject.Location;
import ch.hevs.businessobject.Owner;
import ch.hevs.housesellingservice.Houseselling;

public class BeanHouse  {

	private Houseselling houseselling;
    private String houseDescription;
    private String street;
    private int number;
    private double price;
    private Owner owner;
    private Location location;
    private List<Owner> owners;
    private List<Location> locations;
    private String firstname;
    private String lastname;
    private String city;
    private int nrPools;
    private int nrSkirooms;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to houseselling EJB
    	InitialContext ctx = new InitialContext();
		houseselling = (Houseselling) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/HousesellingBean!ch.hevs.housesellingservice.Houseselling");
			
    	// get locations
		locations = houseselling.getLocations();
		if(locations==null){
			locations = new ArrayList<>(); 
    	}
		
				
		//get owners
		owners = houseselling.getOwners();
		if(owners==null) {
			owners = new ArrayList<>();
		}
		
    }


    
    //Getter & Setter
    
  
    
	public Houseselling getHouseselling() {
		return houseselling;
	}


	public int getNrPools() {
		return nrPools;
	}

	public void setNrPools(int nrPools) {
		this.nrPools = nrPools;
	}

	public int getNrSkirooms() {
		return nrSkirooms;
	}

	public void setNrSkirooms(int nrSkirooms) {
		this.nrSkirooms = nrSkirooms;
	}


	public void setHouseselling(Houseselling houseselling) {
		this.houseselling = houseselling;
	}

	public String getHouseDescription() {
		return houseDescription;
	}

	public void setHouseDescription(String houseDescription) {
		this.houseDescription = houseDescription;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
		
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void updateOwner(ValueChangeEvent event) {
    	//this.owner = (Owner)event.getNewValue();
			
    }
	
	public void updateLocation(ValueChangeEvent event) {
    	//this.location = (Location)event.getNewValue();
			
    }	
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	//Add chalet
	public void addHouseChalet() {	
		
		Owner owner = houseselling.getOwnerLastname(lastname);
		Location location = houseselling.getLocation(city);
		houseselling.addHouseChalet(houseDescription, street, number, price, location, owner, nrSkirooms);
			
		
	}
	
	//add villa
	public void addHouseVilla() {	
		
		Owner owner = houseselling.getOwnerLastname(lastname);
		Location location = houseselling.getLocation(city);
		houseselling.addHouseVilla(houseDescription, street, number, price, location, owner, nrPools);
		
	}
	
	

	
    
    
    
}
