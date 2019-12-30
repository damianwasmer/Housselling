package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Villa extends House {
	private int nrpools;
	
	//Constructors
	public Villa(String street, int number, Owner owner,
			String description, Location location, double price) {
		super(street, number, owner, description, location, price);
	}
	
	public Villa(String street, int number, Owner owner,
			String description, Location location, double price, int nrpools) {
		super(street, number, owner, description, location, price);
		this.nrpools = nrpools;
	}
	
	public Villa() {
		super();
	}

	public int getNrpools() {
		return nrpools;
	}

	public void setNrpools(int nrpools) {
		this.nrpools = nrpools;
	}
		
}

		
