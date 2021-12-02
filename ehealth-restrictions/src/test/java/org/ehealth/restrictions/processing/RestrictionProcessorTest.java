package org.ehealth.restrictions.processing;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.ehealth.restrictions.common.DataHelper;
import org.ehealth.restrictions.endpoints.dto.PersonMedRecord;
import org.ehealth.restrictions.endpoints.dto.certificates.MedCertificateDTO;
import org.ehealth.restrictions.endpoints.dto.medtests.MedTestDTO;
import org.ehealth.restrictions.entities.Restriction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
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
		Restriction.persist(DataHelper.getRestrictions());

		List<Restriction> globals = processor.getGlobalRestrictions();

		assertContains(globals, 1, 2, 3);
	}

	@Test
	void retrieveVaccinatedRestrictions() {
		Restriction.persist(DataHelper.getRestrictions());

		PersonMedRecord record = DataHelper.getPatientRecord();
		record.certificates = List.of(new MedCertificateDTO());

		List<Restriction> processed = processor.process(record);

		assertContains(processed, 1, 2, 3, 10, 11, 12);
	}

	@Test
	void retrieveNonVaccinatedORPostContractionRestrictions() {
		Restriction.persist(DataHelper.getRestrictions());

		PersonMedRecord record = DataHelper.getPatientRecord();

		List<Restriction> processed = processor.process(record);

		assertContains(processed, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
	}

	@Test
	void retrieveTestedRestrictions() {
		Restriction.persist(DataHelper.getRestrictions());

		PersonMedRecord record = DataHelper.getPatientRecord();
		record.tests = List.of(new MedTestDTO());

		List<Restriction> processed = processor.process(record);

		assertContains(processed, 1, 2, 3, 13, 14, 15);
	}

	@Test
	void retrieveRestrictionsForIdealCase() {
		Restriction.persist(DataHelper.getRestrictions());

		PersonMedRecord record = DataHelper.getPatientRecord();
		record.tests = List.of(new MedTestDTO());
		record.certificates = List.of(new MedCertificateDTO());

		List<Restriction> processed = processor.process(record);

		assertContains(processed, 1, 2, 3);
	}

	private void assertContains(List<Restriction> globals, Integer... titles) {
		assertThat(globals.stream().map(f -> Integer.parseInt(f.getTitle()))).containsExactlyInAnyOrder(titles);
	}
}
