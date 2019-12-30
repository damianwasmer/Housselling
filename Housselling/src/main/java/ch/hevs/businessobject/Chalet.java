package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Chalet extends House{
	private int nrskirooms;
	
	//Constructors
	public Chalet(String street, int number, Owner owner,
			String description, Location location, double price) {
		super(street, number, owner, description, location, price);
	}
	
	public Chalet(String street, int number, Owner owner,
			String description, Location location, double price, int nrskirooms) {
		super(street, number, owner, description, location, price);
		this.nrskirooms = nrskirooms;
	}
	
	public Chalet() {
		super();
	}

	public int getNrskirooms() {
		return nrskirooms;
	}

	public void setNrskirooms(int nrskirooms) {
		this.nrskirooms = nrskirooms;
	}
		
}
