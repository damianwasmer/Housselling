package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

@Entity
@Table(name="House")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name="street")
	private String street;
	@Column(name="number")
	private int number;	
	private String description;
	@Column(name="price")
	private double price;
	
	// relations
	@ManyToOne(targetEntity = Owner.class, cascade=CascadeType.MERGE)
	@JoinColumn(name = "FK_OWNER")
	private Owner owner;
	
	@ManyToOne(targetEntity = Location.class, cascade=CascadeType.MERGE)
	@JoinColumn(name = "FK_LOCATION")
	private Location location;
	
	
	// constructors
	public House() {
		super();
	}
	
	public House(String street, int number,String description, double price,
			Location location, Owner owner) {
		super();
		this.street = street;
		this.number = number;
		this.owner = owner;
		this.description = description;
		this.location = location;
		this.price = price;
	}

	//id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//Street
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	//number
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	//price
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	//Description
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", street=" + street + ", number=" + number + ", description=" + description
				+ ", price=" + price + ", owner=" + owner + ", location=" + location + "]";
	}
	
	
}
