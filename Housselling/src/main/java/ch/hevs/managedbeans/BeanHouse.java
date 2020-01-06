package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Chalet;
import ch.hevs.businessobject.House;
import ch.hevs.businessobject.Location;
import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.Villa;
import ch.hevs.housesellingservice.Houseselling;

public class BeanHouse  {

	private Houseselling houseselling;
    private String houseDescription;
    private String street;
    private int number;
    private double price;
    private List<Owner> owners;
    private List<Location> locations;
    private List<Villa> villas;
    private List<Chalet> chalets;
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
		
		//get villas
		villas = houseselling.getVillas();
		if(villas==null) {
			villas = new ArrayList<>();
		}
		
		//get owners
		chalets = houseselling.getChalets();
		if(chalets==null) {
			chalets = new ArrayList<>();
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
	
	public List<Villa> getVillas() {
		return villas;
	}


	public void setVillas(List<Villa> villas) {
		this.villas = villas;
	}


	public List<Chalet> getChalets() {
		return chalets;
	}


	public void setChalets(List<Chalet> chalets) {
		this.chalets = chalets;
	}

	//Delete House
	public void deleteHouse(House house) {
		houseselling.deleteHouse(house);
		
		if(house instanceof Villa){
			villas.remove(house); 
		}
		if(house instanceof Chalet){
			chalets.remove(house); 
		}		
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
