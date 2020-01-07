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
    private List<House> ourHouses;
    private List<House> soldHouses;
    private List<Owner> owners;
    private List<Location> locations;
    private List<Villa> ourvillas;
    private List<Chalet> ourchalets;
    private List<Villa> soldvillas;
    private List<Chalet> soldchalets;
    private String lastname;
    private String city;
    private int nrPools;
    private int nrSkirooms;
    private House houseSelected = new House();
    private boolean isvilla;
    private long id = 2;
    
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
		
		id = houseselling.getIdOwner("Company");
		
		updateourList();
		updatesoldList();
		
    }

    //Update lists
    public void updateourList() {
    	ourHouses = houseselling.getourHouses(id);
		ourvillas = new ArrayList<>();
		ourchalets = new ArrayList<>();
		for (House house : ourHouses){
			if(house instanceof Villa) {
				Villa villa = (Villa) house;
				ourvillas.add(villa);
			} else {
				Chalet chalet = (Chalet) house;
				ourchalets.add(chalet);
			} 
		}
    }
    
    public void updatesoldList() {
    	soldHouses = houseselling.getsoldHouses(id);
		soldvillas = new ArrayList<>();
		soldchalets = new ArrayList<>();
		for (House house : soldHouses){
			if(house instanceof Villa) {
				Villa villa = (Villa) house;
				soldvillas.add(villa);
			} else {
				Chalet chalet = (Chalet) house;
				soldchalets.add(chalet);
			} 
		}
    }

    
    //Getter & Setter    
	public Houseselling getHouseselling() {
		return houseselling;
	}

	public List<House> getSoldHouses() {
		return soldHouses;
	}


	public void setSoldHouses(List<House> soldHouses) {
		this.soldHouses = soldHouses;
	}


	public List<Villa> getSoldvillas() {
		updatesoldList();
		return soldvillas;
	}


	public void setSoldvillas(List<Villa> soldvillas) {
		this.soldvillas = soldvillas;
	}


	public List<Chalet> getSoldchalets() {
		updatesoldList();
		return soldchalets;
	}


	public void setSoldchalets(List<Chalet> soldchalets) {
		this.soldchalets = soldchalets;
	}


	public List<House> getOurHouses() {
		updateourList();
		return ourHouses;
	}


	public void setOurHouses(List<House> ourHouses) {
		this.ourHouses = ourHouses;
	}


	public List<Villa> getOurvillas() {
		updateourList();
		return ourvillas;
	}


	public List<Chalet> getOurchalets() {
		return ourchalets;
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
		owners = houseselling.getOwners();
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

	public List<Location> getLocations() {
		locations = houseselling.getLocations();
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
	
	public void setOurvillas(List<Villa> ourvillas) {
		this.ourvillas = ourvillas;
	}

	public void setOurchalets(List<Chalet> ourchalets) {
		this.ourchalets = ourchalets;
	}

	public boolean isIsvilla() {
		return isvilla;
	}


	public void setIsvilla(boolean isvilla) {
		this.isvilla = isvilla;
	}


	public House getHouseSelected() {
		return houseSelected;
	}


	public void setHouseSelected(House houseSelected) {
		if(houseSelected instanceof Villa) {
			isvilla = true;
		} else {
			isvilla = false;
		}
		this.houseSelected = houseSelected;
	}


	//Delete House
	public void deleteHouse(House house) {
		houseselling.deleteHouse(house);	
	}
	
	//edit house
	public String editHouse() {
		Owner owner = houseselling.getOwnerLastname(lastname);
		Location location = houseselling.getLocation(city);
		houseSelected.setOwner(owner);
		houseSelected.setLocation(location);
		
		if(houseSelected instanceof Villa) {
			Villa villa = (Villa) houseSelected;
			houseselling.editVilla(villa);
		} else {
			Chalet chalet = (Chalet) houseSelected;
			houseselling.editChalet(chalet);
		}
		
		return "welcomeHouseselling";
	}

	//Add chalet
	public String addHouseChalet() {	
		
		Owner owner = houseselling.getOwnerLastname(lastname);
		Location location = houseselling.getLocation(city);
		houseselling.addHouseChalet(houseDescription, street, number, price, location, owner, nrSkirooms);
		
		return "welcomeHouseselling";
	}
	
	//add villa
	public String addHouseVilla() {	
		
		Owner owner = houseselling.getOwnerLastname(lastname);
		Location location = houseselling.getLocation(city);
		houseselling.addHouseVilla(houseDescription, street, number, price, location, owner, nrPools);
		
		return "welcomeHouseselling";
	
	}
    
}
