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
	@Column(name="city")
	private String city;
	@Column(name="postCode")
	private String postCode;

	// relations
	@OneToMany(mappedBy = "location", cascade = CascadeType.PERSIST, orphanRemoval=true)
	private List<House> houses;
	
	//Constructors
	public Location() {
		super();
		houses = new ArrayList<House>();
	}
	
	public Location(String city, String postCode, List<House> houses) {
		super();
		this.city = city;
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
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
		String result = id + "-" + city + "-" + postCode;
		return result;
	}
	
}
