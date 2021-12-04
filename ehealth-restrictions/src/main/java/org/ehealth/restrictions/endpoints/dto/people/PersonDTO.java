package org.ehealth.restrictions.endpoints.dto.people;

/**
 * Person DTO as defined by the <strong>ehealth-people</strong> endpoint
 */
public class PersonDTO {
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

	public PersonDTO(String name, String age, String sex, String address, String phoneNum) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.telephone = phoneNum;
		this.sex = sex;
	}
}
