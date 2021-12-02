package org.ehealth.restrictions.endpoints.dto;

import org.ehealth.restrictions.endpoints.dto.certificates.MedCertificateDTO;
import org.ehealth.restrictions.endpoints.dto.medtests.MedTestDTO;
import org.ehealth.restrictions.endpoints.dto.people.PersonDTO;

import java.util.List;

public class PersonMedRecord {
	public PersonDTO person;

	public List<MedCertificateDTO> certificates;

	public List<MedTestDTO> tests;

	public PersonMedRecord(PersonDTO person, List<MedCertificateDTO> medCerts, List<MedTestDTO> medTests) {
		this.person = person;
		certificates = medCerts;
		tests = medTests;
	}
}
