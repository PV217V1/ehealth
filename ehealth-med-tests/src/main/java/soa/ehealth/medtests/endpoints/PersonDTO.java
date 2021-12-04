package soa.ehealth.medtests.endpoints;

/**
 * Person DTO as defined by the <strong>ehealth-people</strong> endpoint
 */
public class PersonDTO {

	/**
	 * The database assigned ID
	 */
	public Long id;

	/**
	 * Name and Surname of the person
	 */
	public String name;

	/**
	 * The age of the person as a string for some reason
	 */
	public String age;

	/**
	 * The sex of the person
	 */
	public String sex;

	/**
	 * Address provided by the person
	 */
	public String address;

	/**
	 * Phone contact for the person
	 */
	public String telephone;

	public PersonDTO() { }

	public PersonDTO(Long id, String name, String age, String address, String phoneNum) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.telephone = phoneNum;
	}
}