package org.ehealth.restrictions.endpoints.dto.patients;

/**
 * Patient DTO as defined by the <strong>ehealth-patients</strong> endpoint
 */
public class PatientDTO {
	/**
	 * Name and Surname of the patient
	 */
	public String name;

	/**
	 * The age of the patient as a string for some reason
	 */
	public String age;

	/**
	 * The sex of the patient
	 */
	public String sex;

	/**
	 * Address provided by the patient
	 */
	public String address;

	/**
	 * Phone contact for the patient
	 */
	public String telephone;


	public PatientDTO() { }

	public PatientDTO(String name, String age, String sex, String address, String phoneNum) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.telephone = phoneNum;
		this.sex = sex;
	}
}
