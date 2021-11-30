package org.ehealth.restrictions.processing;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.ehealth.restrictions.endpoints.dto.PatientMedRecord;
import org.ehealth.restrictions.endpoints.dto.certificates.MedCertificateDTO;
import org.ehealth.restrictions.endpoints.dto.medtests.MedTestDTO;
import org.ehealth.restrictions.endpoints.dto.patients.PatientDTO;
import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.entities.RestrictionScope;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@Transactional
public class RestrictionProcessorTest {

	@Inject
	RestrictionProcessor processor;

	@BeforeEach
	void setup() {
		Restriction.deleteAll();
	}

	@Test
	void retrieveGlobalRestrictionsOnly() {
		Restriction.persist(getRestrictions());

		List<Restriction> globals = processor.getGlobalRestrictions();

		assertContains(globals, 1, 2, 3);
	}

	@Test
	void retrieveVaccinatedRestrictions() {
		Restriction.persist(getRestrictions());

		PatientMedRecord record = getPatientRecord();
		record.certificates = List.of(new MedCertificateDTO());

		List<Restriction> processed = processor.process(record);

		assertContains(processed, 1, 2, 3, 10, 11, 12);
	}

	@Test
	void retrieveNonVaccinatedORPostContractionRestrictions() {
		Restriction.persist(getRestrictions());

		PatientMedRecord record = getPatientRecord();

		List<Restriction> processed = processor.process(record);

		assertContains(processed, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
	}

	@Test
	void retrieveTestedRestrictions() {
		Restriction.persist(getRestrictions());

		PatientMedRecord record = getPatientRecord();
		record.tests = List.of(new MedTestDTO());

		List<Restriction> processed = processor.process(record);

		assertContains(processed, 1, 2, 3, 13, 14, 15);
	}

	@Test
	void retrieveRestrictionsForIdealCase() {
		Restriction.persist(getRestrictions());

		PatientMedRecord record = getPatientRecord();
		record.tests = List.of(new MedTestDTO());
		record.certificates = List.of(new MedCertificateDTO());

		List<Restriction> processed = processor.process(record);

		assertContains(processed, 1, 2, 3);
	}

	private void assertContains(List<Restriction> globals, Integer... titles) {
		assertThat(globals.stream().map(f -> Integer.parseInt(f.getTitle()))).containsExactlyInAnyOrder(titles);
	}

	public static List<Restriction> getRestrictions() {
		return List.of(
				new Restriction("1", "Description 1",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.GLOBAL),
				new Restriction("2", "Description 2",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.GLOBAL),
				new Restriction("3", "Description 3",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.GLOBAL),
				new Restriction("4", "Description 4",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.NOT_VACCINATED),
				new Restriction("5", "Description 5",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.NOT_VACCINATED),
				new Restriction("6", "Description 6",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.NOT_VACCINATED),
				new Restriction("7", "Description 7",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.POST_CONTRACTION),
				new Restriction("8", "Description 8",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.POST_CONTRACTION),
				new Restriction("9", "Description 9",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.POST_CONTRACTION),
				new Restriction("10", "Description 10",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.VACCINATED),
				new Restriction("11", "Description 11",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.VACCINATED),
				new Restriction("12", "Description 12",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.VACCINATED),
				new Restriction("13", "Description 13",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.TESTED),
				new Restriction("14", "Description 14",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.TESTED),
				new Restriction("15", "Description 15",
						LocalDate.of(2021, Month.APRIL, 20),
						LocalDate.of(2021, Month.MAY, 20), RestrictionScope.TESTED)
		);
	}

	public static PatientMedRecord getPatientRecord() {
		PatientDTO patient = new PatientDTO();
		patient.name = "John";
		patient.age = "28";
		patient.address = "Some Address";
		patient.telephone = "012345678";
		return new PatientMedRecord(patient, List.of(), List.of());
	}
}
