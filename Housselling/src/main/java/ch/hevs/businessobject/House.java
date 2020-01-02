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
@Inheritance(strategy = InheritanceType.JOINED)
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
	@ManyToOne(targetEntity = Owner.class)
	@JoinColumn(name = "FK_OWNER")
	private long idOwner;
	
	@ManyToOne(targetEntity = Location.class)
	@JoinColumn(name = "FK_LOCATION")
	private long idLocation;
	
	
	// constructors
	public House() {
		super();
	}
	
	public House(String street, int number,String description, double price,
			long idLocation, long idOwner) {
		super();
		this.street = street;
		this.number = number;
		this.idOwner = idOwner;
		this.description = description;
		this.idLocation = idLocation;
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

	public long getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(long idOwner) {
		this.idOwner = idOwner;
	}

	public long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(long idLocation) {
		this.idLocation = idLocation;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", street=" + street + ", number=" + number + ", description=" + description
				+ ", price=" + price + ", idOwner=" + idOwner + ", idLocation=" + idLocation + "]";
	}


		
	
	
}
