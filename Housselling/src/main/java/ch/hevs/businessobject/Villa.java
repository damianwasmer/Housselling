package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Villa extends House {
	private int nrpools;
	
	//Constructors
	public Villa(String street, int number,String description, double price,
			long idLocation, long idOwner) {
		super(street, number, description, price, idLocation, idOwner);
	}
	
	public Villa(String street, int number,String description, double price,
			long idLocation, long idOwner, int nrpools) {
		super(street, number, description, price, idLocation, idOwner);
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

		
