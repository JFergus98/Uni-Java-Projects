/**
 * Stores details on a health profestional
 * 
 * @author Jamie Fergus 190018054
 *
 */
public class HealthProfessional {
	private String name, profession, location;
	
	/** 
	 * default constructor
	 * 
	 */
	public HealthProfessional() {
		name = null;
		profession = null;
		location = null;
	}
	
	/**
	 * alternative constructor
	 * 
	 * @param name - String holding name of Health Professional
	 * @param profession - String holding profession of Health Professional
	 * @param location - String holding office location/workplace of Health Professional
	 */
	public HealthProfessional(String name, String profession, String location) {
		this.name = name;
		this.profession = profession;
		this.location = location;
	}

	/**
	 * get name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * sets the name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * gets their profession
	 * 
	 * @return their profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * sets their profession
	 * 
	 * @param profession
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	/**
	 * gets the location of office/workspace
	 * 
	 * @return their location of office/workspace
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * sets the location of office/workspace
	 * 
	 * @param location of office/workspace
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}
