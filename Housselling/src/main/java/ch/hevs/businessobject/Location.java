package ch.hevs.businessobject;

import java.util.ArrayList;
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
@Table(name="Location")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="location")
	private String location;
	@Column(name="postCode")
	private String postCode;

	// relations
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)//@JoinColumn(name = "FK_CLIENT")
	private List<House> houses;
	
	//Constructors
	public Location() {
		super();
		houses = new ArrayList<House>();
	}
	
	public Location(String location, String postCode, List<House> houses) {
		super();
		this.location = location;
		this.postCode = postCode;
		houses = new ArrayList<House>();
	}

	//id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	//location
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	//postcode
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	//houses
	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}
	
	public void addHouse(House house) {
		houses.add(house);
	}
	
	public void removeHouse(House house) {
		houses.remove(house);
	}
	
	@Override
	public String toString() {
		String result = id + "-" + location + "-" + postCode;
		return result;
	}
	
}
