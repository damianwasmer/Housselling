package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Owner;
import ch.hevs.housesellingservice.Houseselling;

public class BeanOwner {
	
    private Houseselling houseselling;
    private String name;
    private String firstname;
    private String lastname;
    private String updateFirstname;
    private String updateLastname;
    private List<String> ownerNames;
    private List<Owner> owners;
    private List<Owner> owneredit; 
    
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
		 
		owneredit = new ArrayList<>(); 
    	
		
		this.ownerNames = new ArrayList<String>();
		this.ownerNames.add("-");
		for (Owner owner : owners) {
			this.ownerNames.add(owner.getLastname());
		}
    }
    
    
    
    public List<String> getOwnerNames() {
		return ownerNames;
	}



	public void setOwnerNames(List<String> ownerNames) {
		this.ownerNames = ownerNames;
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

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
		
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(final String lastname) {
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



	public List<Owner> getOwneredit() {
		return owneredit;
	}



	public void setOwneredit(List<Owner> owneredit) {
		this.owneredit = owneredit;
	}



	public String addOwner() {
    	
    	houseselling.addOwner(firstname, lastname);
    	
    	//Go to this page
    	return "showOwners";

    }

	public void deleteOwner(Owner owner){
		houseselling.deleteOwner(owner);
	}
	
	public void execute(Owner owner) {

		updateLastname = owner.getLastname();
		updateFirstname = owner.getFirstname();
		owneredit.add(owner);

		//return "/updateOwner.xhtml?faces-redirect=true";
	}
	
	public void editOwner() {
		for (Owner owner : owneredit){
			houseselling.editOwner(owner);
		}
		owners = houseselling.getOwners();
	}
	
	
	@Override
	public String toString() {
		return " firstname" + " " + "lastname";
	}
	
	

}
