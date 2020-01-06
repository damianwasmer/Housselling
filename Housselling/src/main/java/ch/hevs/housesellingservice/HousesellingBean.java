package ch.hevs.housesellingservice;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Chalet;
import ch.hevs.businessobject.House;
import ch.hevs.businessobject.Location;
import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.Villa;

@Stateful
public class HousesellingBean implements Houseselling {

	@PersistenceContext(name = "HousesellingPU")
	private EntityManager em;
	EntityTransaction tx = null;

	
	//Owner-------------------------------------
	@Override
	public List<Owner> getOwners() {
		// TODO Auto-generated method stub
		return em.createQuery("FROM Owner").getResultList();
	}
	
	@Override
	public void addOwner(String firstname, String lastname, String language) {
		// TODO Auto-generated method stub
		Owner o = new Owner(firstname,lastname,language);
				
		em.persist(o);
	}

	@Override
	public Owner getHouseOwner(String firstname, String lastname) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select h.owner from House h where h.firstname = :firstname && h.lastname = :lastname"); 
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname); 
		
		
		return (Owner)query.getSingleResult(); 
	}

	@Override
	public Owner getOwnerEntity(String firstname, String lastname) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select o from Owner o where o.firstname = :firstname and o.lastname = :lastname");
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname); 
		
		return (Owner)query.getSingleResult(); 
	}

	@Override
	public void deleteOwner(Owner owner) {
		// TODO Auto-generated method stub
		Owner ownerdelete = em.merge(owner);
		em.remove(ownerdelete);
	}

	@Override
	public void editOwner(Owner owner) {
		// TODO Auto-generated method stub
		em.merge(owner);
	}
	
	@Override
	public Owner getOwnerLastname(String lastname) {
		Query query = em.createQuery("Select o FROM Owner o WHERE o.lastname=:lastname");
		query.setParameter("lastname", lastname);
		
		Owner owner = (Owner)query.getSingleResult();
		System.out.println("ID Owner called from getOwnerLastname(): "+owner.getId());
		return owner;
	}
	
	public Owner getOwner(long idOwner) {
		Query query = em.createQuery("FROM Owner o where o.id=:id");
		query.setParameter("id", idOwner);
		return (Owner) query.getSingleResult();
	}
	
	//House------------------------------------------
	@Override
	public House getHouse(String HouseDescription, String lastnameOwner) {
		Query query = em.createQuery("FROM House a WHERE a.description=:description AND a.owner.lastname=:lastname");
		query.setParameter("description", HouseDescription);
		query.setParameter("lastname", lastnameOwner);
		
		House house = (House) query.getSingleResult();
		System.out.println("ID House called from getHouse(): "+house.getId());
		return house;
	}

	@Override
	public List<House> getHouseListFromOwner(String firstname, String lastname) {
		Query query = em.createQuery("SELECT c.owners FROM Owner c where c.lastname=:lastname && c.firstname=:firstname");
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname);
		
		return (List<House>) query.getResultList();
	}

	
	@Override
	public void addHouse(String houseDescription, String street, int number, double price, Location location, Owner owner) {
		House h = new House();
		h.setDescription(houseDescription);
		h.setStreet(street);
		h.setNumber(number);
		h.setPrice(price);
		h.setLocation(location);
		h.setOwner(owner);
			
		em.persist(h);
	}
	
	//Add chalet
	@Override
	public void addHouseChalet(String houseDescription, String street, int number, double price, Location location,
			Owner owner, int nrRooms) {
		Chalet c = new Chalet();
		c.setDescription(houseDescription);
		c.setStreet(street);
		c.setNumber(number);
		c.setPrice(price);
		c.setLocation(location);
		c.setOwner(owner);
		c.setNrskirooms(nrRooms);			
		em.persist(c);
		
	}

	//Add villa
	@Override
	public void addHouseVilla(String houseDescription, String street, int number, double price, Location location,
			Owner owner, int nrPools) {
		Villa v = new Villa();
		v.setDescription(houseDescription);
		v.setStreet(street);
		v.setNumber(number);
		v.setPrice(price);
		v.setLocation(location);
		v.setOwner(owner);
		v.setNrpools(nrPools);		
		em.persist(v);
		
	}
	
	@Override
	public void deleteHouse(House house) {
		// TODO Auto-generated method stub
		House housedelete = em.merge(house);
		em.remove(housedelete);
		
	}
	
	@Override
	public List<Villa> getVillas() {
		// TODO Auto-generated method stub
		return em.createQuery("FROM Villa").getResultList();
	}

	@Override
	public List<Chalet> getChalets() {
		return em.createQuery("FROM Chalet").getResultList();
	}
	
	//Location---------------------------------------
	@Override
	public List<Location> getLocations() {
		// TODO Auto-generated method stub
		return em.createQuery("FROM Location").getResultList();
	}

	@Override
	public void addLocation(String city, String postcode) {
		// TODO Auto-generated method stub
		Location l = new Location();
		l.setCity(city);
		l.setPostCode(postcode);
		em.persist(l);
	}

	@Override
	public long getIdOwner(String lastname) {
		Query query = em.createQuery("FROM Owner o WHERE o.lastname=:lastname");
		query.setParameter("lastname", lastname);
		
		Owner owner = (Owner)query.getSingleResult();
		return owner.getId();
	}

	@Override
	public long getIdLocation(String city) {
		Query query = em.createQuery("FROM Location l WHERE l.city=:city");
		query.setParameter("city", city);
		
		Location location = (Location)query.getSingleResult();
		return location.getId();
	}

	@Override
	public void deleteLocation(Location location) {
		// TODO Auto-generated method stub
		Location locationdelete = em.merge(location);
		em.remove(locationdelete);
	}

	@Override
	public Location getLocation(String city) {
		Query query = em.createQuery("Select c FROM Location c WHERE c.city=:city");
		query.setParameter("city", city);
		
		Location location = (Location)query.getSingleResult();
		System.out.println("ID House called from getLocation(): "+location.getId());
		return location;
		
	}

		

}
