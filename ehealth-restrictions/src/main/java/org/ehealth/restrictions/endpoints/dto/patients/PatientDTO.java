package org.ehealth.restrictions.endpoints.dto.patients;

public class PatientDTO {
	public String name;
	public Integer age;
	public String address;
	public String phoneNum;

	public PatientDTO() { }

	public PatientDTO(String name, Integer age, String address, String phoneNum) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNum = phoneNum;
	}
}
