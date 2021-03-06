package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Chalet")
public class Chalet extends House{
	private int nrskirooms;
	
	//Constructors
	public Chalet(String street, int number,String description, double price,
			Location location, Owner owner) {
		super(street, number, description, price, location, owner);
	}
	
	public Chalet(String street, int number,String description, double price,
			Location location, Owner owner, int nrskirooms) {
		super(street, number, description, price, location, owner);
		this.nrskirooms = nrskirooms;
	}
	
	public Chalet() {
		super();
	}
	
	
	//skirooms
	public int getNrskirooms() {
		return nrskirooms;
	}

	public void setNrskirooms(int nrskirooms) {
		this.nrskirooms = nrskirooms;
	}
		
}
