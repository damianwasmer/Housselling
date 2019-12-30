package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class Chalet extends House{
	private boolean skiroom;
	
	//Constructors
	public Chalet(String street, int number, Owner owner,
			String description, Location location, float price) {
		super(street, number, owner, description, location, price);
	}
	
	public Chalet(String street, int number, Owner owner,
			String description, Location location, float price, boolean skiroom) {
		super(street, number, owner, description, location, price);
		this.skiroom = skiroom;
	}
	
	public Chalet() {
		super();
	}

	public boolean isSkiroom() {
		return skiroom;
	}

	public void setSkiroom(boolean skiroom) {
		this.skiroom = skiroom;
	}
		
}
