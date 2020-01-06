package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Owner;
import ch.hevs.housesellingservice.Houseselling;

public class BeanOwner {
	
    private Houseselling houseselling;
    private String name;
    private String firstname;
    private String lastname;
    private String language;
    private String updateFirstname;
    private String updateLastname;
    private List<String> ownerNames;
    private List<Owner> owners;
    private Owner ownerSelected = new Owner();
    private long id;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to houseselling EJB
    	InitialContext ctx = new InitialContext();
		houseselling = (Houseselling) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/HousesellingBean!ch.hevs.housesellingservice.Houseselling");
			
    	// get owners
		owners = houseselling.getOwners();
		if(owners==null){
    		owners = new ArrayList<>(); 
    	}
		
    	//Get owner names
		this.ownerNames = new ArrayList<String>();
		this.ownerNames.add("-");
		for (Owner owner : owners) {
			this.ownerNames.add(owner.getLastname());
		}
    }
    
    //Getter and setter
    public List<String> getOwnerNames() {
		return ownerNames;
	}
    
    public void setOwnerNames(List<String> ownerNames) {
		this.ownerNames = ownerNames;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}


	public List<Owner> getOwners() {
		return owners;
    }
    
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
		
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
		
	}
	
    public Houseselling getHouseselling() {
		return houseselling;
	}

	public void setHouseselling(Houseselling houseselling) {
		this.houseselling = houseselling;
	}
	
	
	public String getUpdateFirstname() {
		return updateFirstname;
	}


	public void setUpdateFirstname(String updateFirstname) {
		this.updateFirstname = updateFirstname;
	}


	public String getUpdateLastname() {
		return updateLastname;
	}


	public void setUpdateLastname(String updateLastname) {
		this.updateLastname = updateLastname;
	}

	public Owner getOwnerSelected() {
		return ownerSelected;
	}

	public void setOwnerSelected(Owner ownerSelected) {
		this.ownerSelected = ownerSelected;
		id=ownerSelected.getId();
		System.out.println(ownerSelected.getFirstname() + " " + ownerSelected.getId());
	}

	//add owner
	public String addOwner() {
    	
    	houseselling.addOwner(firstname, lastname, language);
    	
    	//Go to this page
    	return "showOwners";

    }

	//delete owner
	public void deleteOwner(Owner owner){
		houseselling.deleteOwner(owner);
	}
	
	//edit owner
	public void editOwner() {
		/*Owner owner = houseselling.getOwner(id);
		owner.setFirstname(ownerSelected.getFirstname());
		owner.setLastname(ownerSelected.getLastname());*/
		
		houseselling.editOwner(ownerSelected);
		System.out.println(ownerSelected.getFirstname() + " " + ownerSelected.getLastname() + " " + ownerSelected.getId());

	}
	
	//update id owner
	public void updateIdOwnerEdit(ValueChangeEvent event) {
		ownerSelected = houseselling.getOwner((Long)event.getNewValue());
	}
	
	//toString
	@Override
	public String toString() {
		return " firstname" + " " + "lastname";
	}
	
	

}
