package ch.hevs.housesellingservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Chalet;
import ch.hevs.businessobject.House;
import ch.hevs.businessobject.Location;
import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.Villa;

@Local
public interface Houseselling {
	
	//Owner
	List<Owner> getOwners();
	Owner getHouseOwner(String firstname, String lastname);
	Owner getOwnerEntity(String firstname, String lastname);
	Owner getOwnerLastname(String lastname);
	long getIdOwner(String lastname);
	void addOwner(String firstname, String lastname, String language);
	void deleteOwner(Owner owner);
	void editOwner(Owner owner);
	Owner getOwner(long idOwner);
	
	//Location
	List<Location> getLocations();
	long getIdLocation(String city);
	Location getLocation(String location);
	void addLocation(String location, String postcode);
	void deleteLocation(Location location);
	void editLocation(Location location);
	
	//House
	House getHouse(String HouseDescription, String lastnameOwner);
	List<House> getHouseListFromOwner(String firstname, String lastname);
	void addHouseChalet(String houseDescription, String street, int number, double price, Location location, Owner owner, int nrRooms);
	void addHouseVilla(String houseDescription, String street, int number, double price, Location location, Owner owner, int nrPools);
	List<House>getourHouses(Long id);
	List<House>getsoldHouses(Long id);
	void deleteHouse(House house);
	void editVilla(Villa villa);
	void editChalet(Chalet chalet);
}