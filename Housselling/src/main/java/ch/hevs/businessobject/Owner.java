package ch.hevs.businessobject;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Owner")
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="lastname")
	private String lastname;
	@Column(name="firstname")
	private String firstname;

	// relations
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<House> houses;
	
	// constructors
		public Owner() {
		}
		public Owner(String lastname, String firstname) {
			this.lastname = lastname;
			this.firstname = firstname;
		}

	//id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	//lastname
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	//firstname
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	//House
	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}
	
	
}
