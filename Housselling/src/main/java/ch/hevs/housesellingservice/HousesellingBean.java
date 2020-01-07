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
		return em.createQuery("FROM Owner").getResultList();
	}
	
	@Override
	public void addOwner(String firstname, String lastname, String language) {
		Owner o = new Owner(firstname,lastname,language);
		em.persist(o);
	}

	@Override
	public Owner getHouseOwner(String firstname, String lastname) {
		Query query = em.createQuery("select h.owner from House h where h.firstname = :firstname && h.lastname = :lastname"); 
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname); 
		return (Owner)query.getSingleResult(); 
	}

	@Override
	public Owner getOwnerEntity(String firstname, String lastname) {
		Query query = em.createQuery("select o from Owner o where o.firstname = :firstname and o.lastname = :lastname");
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname); 
		return (Owner)query.getSingleResult(); 
	}

	@Override
	public void deleteOwner(Owner owner) {
		if(owner.getFirstname().equalsIgnoreCase("Sellhouses") && owner.getLastname().equalsIgnoreCase("Company")) {
			return;
		} else {
			Owner ownerdelete = em.merge(owner);
			em.remove(ownerdelete);
		}
	}

	@Override
	public void editOwner(Owner owner) {
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
	
	@Override
	public long getIdOwner(String lastname) {
		Query query = em.createQuery("FROM Owner o WHERE o.lastname=:lastname");
		query.setParameter("lastname", lastname);
		
		Owner owner = (Owner)query.getSingleResult();
		return owner.getId();
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
		House housedelete = em.merge(house);
		em.remove(housedelete);
		
	}

	@Override
	public List<House> getourHouses(Long id) {
		Query query = em.createQuery("Select c.houses FROM Owner c WHERE c.id=:id");
		query.setParameter("id", id);
		return (List<House>) query.getResultList();
	}
	
	@Override
	public List<House> getsoldHouses(Long id) {
		Query query = em.createQuery("Select c.houses FROM Owner c WHERE c.id!=:id");
		query.setParameter("id", id);
		return (List<House>) query.getResultList();
	}
	
	@Override
	public void editVilla(Villa villa) {
		em.merge(villa);
	}
	
	@Override
	public void editChalet(Chalet chalet) {
		em.merge(chalet);
	}
	
	//Location---------------------------------------
	@Override
	public List<Location> getLocations() {
		return em.createQuery("FROM Location").getResultList();
	}

	@Override
	public void addLocation(String city, String postcode) {
		Location l = new Location();
		l.setCity(city);
		l.setPostCode(postcode);
		em.persist(l);
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

	@Override
	public void editLocation(Location location) {
		em.merge(location);
	}

}
