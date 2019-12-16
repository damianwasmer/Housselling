package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.House;
import ch.hevs.businessobject.Owner;
import ch.hevs.housesellingservice.Houseselling;

/**
 * TransferBean.java
 * 
 */

public class TransferBean
{
    private List<Owner> owners;
    private List<String> ownerNames;
    private List<String> sourceHouseDescriptions;
    private List<String> destinationHouseDescriptions;
    private String sourceHouseDescription;
    private String destinationHouseDescription;
    private String sourceownerName;
    private String destinationownerName;
    private String transactionResult;
    private int transactionAmount;
    private Houseselling houseselling;
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to houseselling EJB
    	InitialContext ctx = new InitialContext();
		houseselling = (Houseselling) ctx.lookup("java:global/Housselling/HousesellingBean!ch.hevs.housesellingservice.Houseselling");
			
    	// get owners
		List<Owner> ownerList = houseselling.getOwners();
		this.ownerNames = new ArrayList<String>();
		for (Owner owner : ownerList) {
			this.ownerNames.add(owner.getLastname());
		}
		
		// initialize House descriptions
		this.sourceHouseDescriptions = new ArrayList<String>();
		this.destinationHouseDescriptions = new ArrayList<String>();
		List<House> Houses = houseselling.getHouseListFromOwnerLastname(ownerList.get(0).getLastname());
		this.sourceHouseDescriptions.add(Houses.get(0).getDescription());
		this.destinationHouseDescriptions.add(Houses.get(0).getDescription());
    }
    
    // transactionAmount
    public int getTransactionAmount () {
    	return transactionAmount;
    }
    public void setTransactionAmount (final int transactionAmount) {
    	this.transactionAmount=transactionAmount;
    }
    
    // sourceownerName
    public String getSourceownerName () {
    	return sourceownerName;
    }
    public void setSourceownerName (final String sourceownerName) {
    	this.sourceownerName=sourceownerName;
    }
    
    // sourceHouseDescriptions
    public List<String> getSourceHouseDescriptions () {
    	return sourceHouseDescriptions;
    }
    
    // destinationHouseDescriptions
    public List<String> getDestinationHouseDescriptions () {
    	return destinationHouseDescriptions;
    }
    
    // destinationownerName
    public String getDestinationownerName () {
    	return destinationownerName;
    }
    public void setDestinationownerName (final String destinationownerName) {
    	this.destinationownerName=destinationownerName;
    }
    
    // transactionResult
    public String getTransactionResult () {
    	return transactionResult;
    }
	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}
    
	// sourceHouseDescription
    public String getSourceHouseDescription() {
		return sourceHouseDescription;
	}
	public void setSourceHouseDescription(String sourceHouseDescription) {
		this.sourceHouseDescription = sourceHouseDescription;
	}

	// destinationHouseDescription
	public String getDestinationHouseDescription() {
		return destinationHouseDescription;
	}
	public void setDestinationHouseDescription(
			String destinationHouseDescription) {
		this.destinationHouseDescription = destinationHouseDescription;
	}

	public void updateSourceHouses(ValueChangeEvent event) {
    	this.sourceownerName = (String)event.getNewValue();
    	
	    List<House> Houses = houseselling.getHouseListFromOwnerLastname(this.sourceownerName);
	    this.sourceHouseDescriptions = new ArrayList<String>();
		for (House House : Houses) {
			this.sourceHouseDescriptions.add(House.getDescription());
		}
    }
	public void updateDestinationHouses(ValueChangeEvent event) {
    	this.destinationownerName = (String)event.getNewValue();
			
	    List<House> Houses = houseselling.getHouseListFromOwnerLastname(this.destinationownerName);
	    this.destinationHouseDescriptions = new ArrayList<String>();
		for (House House : Houses) {
			this.destinationHouseDescriptions.add(House.getDescription());
		}
    }

    public List<Owner> getOwners() {
		return owners;
    }
    
    public List<String> getOwnerNames() {
    	return ownerNames;
    }
    
    
    /*public String performTransfer() {
    	
    	try {
			if (sourceownerName.equals(destinationownerName) && sourceHouseDescription.equals(destinationHouseDescription)) {
				
				this.transactionResult="Error: Houses are identical!";
			} 
			else {
				
				House compteSrc = houseselling.getHouse(sourceHouseDescription, sourceownerName);
				House compteDest = houseselling.getHouse(destinationHouseDescription, destinationownerName);
	
				// Transfer
				houseselling.transfer(compteSrc, compteDest, transactionAmount);
				this.transactionResult="Success!";
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

		return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} */
}