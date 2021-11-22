package org.ehealth.restrictions.endpoints.dto;

import org.ehealth.restrictions.endpoints.dto.certificates.MedCertificateDTO;
import org.ehealth.restrictions.endpoints.dto.medtests.MedTestDTO;
import org.ehealth.restrictions.endpoints.dto.patients.PatientDTO;

import java.util.List;

public class PatientMedRecord {
	public PatientDTO patient;

	public List<MedCertificateDTO> certificates;

	public List<MedTestDTO> tests;

	public PatientMedRecord(PatientDTO patient, List<MedCertificateDTO> medCerts, List<MedTestDTO> medTests) {
		this.patient = patient;
		certificates = medCerts;
		tests = medTests;
	}
}
