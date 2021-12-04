package soa.ehealth.medtests.endpoints;

/**
 * Person DTO as defined by the <strong>ehealth-people</strong> endpoint
 */
public class PersonDTO {
	public Long id;
	public String name;
	public String age;
	public String address;
	public String phoneNum;

	public PersonDTO() { }

	public PersonDTO(Long id, String name, String age, String address, String phoneNum) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNum = phoneNum;
	}
}