package ch.hevs.businessobject;

import javax.persistence.Embeddable;

@Embeddable
public class Language {	
	
	private String languageName;
	
	//constructor
	public Language() {}
	
	public Language(String languageName) {
		this.languageName = languageName;
	}

	//getter and setter
	public String getName() {
		return languageName;
	}

	public void setName(String languageName) {
		this.languageName = languageName;
	}

}
