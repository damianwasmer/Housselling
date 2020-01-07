package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Villa")
public class Villa extends House {
	private int nrpools;
	
	//Constructors
	public Villa(String street, int number,String description, double price,
			Location location, Owner owner) {
		super(street, number, description, price, location, owner);
	}
	
	public Villa(String street, int number,String description, double price,
			Location location, Owner owner, int nrpools) {
		super(street, number, description, price, location, owner);
		this.nrpools = nrpools;
	}
	
		
	public Villa() {
		super();
	}
	
	
	//nrPools
	public int getNrpools() {
		return nrpools;
	}

	public void setNrpools(int nrpools) {
		this.nrpools = nrpools;
	}
		
}

		
