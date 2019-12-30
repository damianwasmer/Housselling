package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Villa extends House {
	private boolean pool;
	
	//Constructors
	public Villa(String street, int number, Owner owner,
			String description, Location location, float price) {
		super(street, number, owner, description, location, price);
	}
	
	public Villa(String street, int number, Owner owner,
			String description, Location location, float price, boolean pool) {
		super(street, number, owner, description, location, price);
		this.pool = pool;
	}
	
	public Villa() {
		super();
	}

	public boolean isPool() {
		return pool;
	}

	public void setPool(boolean pool) {
		this.pool = pool;
	}
		
}

		
