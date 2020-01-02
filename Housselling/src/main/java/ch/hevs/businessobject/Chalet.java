package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Chalet extends House{
	private int nrskirooms;
	
	//Constructors
	public Chalet(String street, int number,String description, double price,
			long idLocation, long idOwner) {
		super(street, number, description, price, idLocation, idOwner);
	}
	
	public Chalet(String street, int number,String description, double price,
			long idLocation, long idOwner, int nrskirooms) {
		super(street, number, description, price, idLocation, idOwner);
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
