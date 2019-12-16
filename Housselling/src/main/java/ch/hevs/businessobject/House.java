package ch.hevs.businessobject;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name="street")
	private String street;
	@Column(name="number")
	private int number;	
	private String description;
	
	// relations
	@ManyToOne
	@JoinColumn(name = "FK_OWNER")
	private Owner owner;
	
	@ManyToOne
	@JoinColumn(name = "FK_LOCATION")
	private Location location;
	
	// constructors
		public House() {
		}
		public House(String street, int number, Owner owner,
				String description) {
			this.street = street;
			this.number = number;
			this.owner = owner;
			this.description = description;
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

	//Description
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//Owner
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
		
	//toString
	@Override
	public String toString() {
		return "House [id=" + id + ", street=" + street + ", number=" + number + ", description=" + description
				+ ", owner=" + owner + "]";
	}
	
	
}
