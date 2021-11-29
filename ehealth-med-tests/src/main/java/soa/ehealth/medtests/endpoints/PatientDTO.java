package soa.ehealth.medtests.endpoints;

public class PatientDTO {
	public Long id;
	public String name;
	public Integer age;
	public String address;
	public String phoneNum;

	public PatientDTO() { }

	public PatientDTO(Long id, String name, Integer age, String address, String phoneNum) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNum = phoneNum;
	}
}