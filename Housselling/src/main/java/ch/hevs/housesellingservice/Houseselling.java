package ch.hevs.housesellingservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.House;
import ch.hevs.businessobject.Location;
import ch.hevs.businessobject.Owner;

@Local
public interface Houseselling {
	
	//Owner
	List<Owner> getOwners();
	Owner getHouseOwner(String firstname, String lastname);
	Owner getOwnerEntity(String firstname, String lastname);
	Owner getOwnerLastname(String lastname);
	long getIdOwner(String lastname);
	void addOwner(String firstname, String lastname);
	void deleteOwner(Owner owner);
	void editOwner(Owner owner);
	
	//Location
	List<Location> getLocations();
	long getIdLocation(String city);
	Location getLocation(String location);
	void addLocation(String location, String postcode);
	void deleteLocation(Location location);
	
	//House
	void addHouse(String houseDescription, String street, int number, double price, Location location, Owner owner);
	void addChalet(int nrSkirooms);
	void addVilla(int nrPools);
	House getHouse(String HouseDescription, String lastnameOwner);
	List<House> getHouseListFromOwner(String firstname, String lastname);
	void transfer(House compteSrc, House compteDest, int montant) throws Exception;
	void addHouseChalet(String houseDescription, String street, int number, double price, Location location, Owner owner, int nrRooms);
	void addHouseVilla(String houseDescription, String street, int number, double price, Location location, Owner owner, int nrPools);



}