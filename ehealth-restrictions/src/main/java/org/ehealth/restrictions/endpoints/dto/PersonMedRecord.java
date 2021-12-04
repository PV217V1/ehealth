package org.ehealth.restrictions.endpoints.dto;

import org.ehealth.restrictions.endpoints.dto.certificates.MedCertificateDTO;
import org.ehealth.restrictions.endpoints.dto.medtests.MedTestDTO;
import org.ehealth.restrictions.endpoints.dto.people.PersonDTO;

import java.util.List;

/**
 * Combined information about the person including certificate and all tests
 */
public class PersonMedRecord {
	public PersonDTO person;

	public MedCertificateDTO certificate;

	public List<MedTestDTO> tests;

	public PersonMedRecord(PersonDTO person, MedCertificateDTO medCert, List<MedTestDTO> medTests) {
		this.person = person;
		certificate = medCert;
		tests = medTests;
	}
}
