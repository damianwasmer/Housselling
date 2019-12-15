package ch.hevs.bankservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.House;
import ch.hevs.businessobject.Owner;

@Local
public interface Houseselling {

	House getHouse(String HouseDescription, String lastnameOwner);

	List<House> getHouseListFromOwnerLastname(String lastname);

	List<Owner> getOwners();

	Owner getOwner(long Ownerid);

}