package soa.ehealth.tmp;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "e_patient")
@Cacheable
public class Patient extends PanacheEntity {
	@Column(length = 40, unique = true)
	public String name;
	@Column(length = 40)
	public Integer age;
	@Column(length = 150)
	public String address;
	@Column(length = 10)
	public String phoneNum;

	public Patient() { }

	public Patient(String name, Integer age, String address, String phoneNum) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNum = phoneNum;
	}
}
