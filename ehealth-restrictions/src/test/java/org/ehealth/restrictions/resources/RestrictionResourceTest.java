package org.ehealth.restrictions.resources;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.ehealth.restrictions.common.DataHelper;
import org.ehealth.restrictions.endpoints.CertificateEndpoint;
import org.ehealth.restrictions.endpoints.MedTestsEndpoint;
import org.ehealth.restrictions.endpoints.PatientEndpoint;
import org.ehealth.restrictions.endpoints.dto.PatientMedRecord;
import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.entities.RestrictionScope;
import org.ehealth.restrictions.processing.RestrictionProcessorTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@Transactional
public class RestrictionResourceTest {

	@BeforeEach
	void setup() {
		Restriction.deleteAll();
		Restriction.persist(DataHelper.getRestrictions());
	}

	@Test
	public void create() {
		Restriction r = new Restriction("A", "Desc A",
				LocalDate.of(2021, Month.AUGUST, 20),
				LocalDate.of(2021, Month.AUGUST, 22),
				LocalDate.of(2021, Month.SEPTEMBER, 20),
				RestrictionScope.GLOBAL);

		given()
				.body(r)
				.contentType("application/json")
				.when().post("/restrictions/create")
				.then()
				.statusCode(201)
				.body("title", equalTo("A"))
				.body("description", equalTo("Desc A"));
	}

	@Test
	public void retrieveAll() {
		given()
				.when().get("/restrictions")
				.then()
				.statusCode(200)
				.body("size()", is(15));
	}

	@Test
	public void retrieveAllGlobalScoped() {
		given()
				.when().get("/restrictions/scoped/GLOBAL")
				.then()
				.statusCode(200)
				.body("size()", is(3));
	}


	@Test
	public void retrieveForDate() {
		given()
				.when().get("/restrictions/forDate/" + "2021-08-22")
				.then()
				.statusCode(200)
				.body("title", containsInAnyOrder("5", "6", "7", "8"));
	}

	@InjectMock
	@RestClient
	PatientEndpoint patients;

	@InjectMock
	@RestClient
	CertificateEndpoint certs;

	@InjectMock
	@RestClient
	MedTestsEndpoint medTests;

	@Test
	public void retrieveAllForUser() {
		when(patients.findById(1L)).thenReturn(DataHelper.getPatientRecord().patient);
		when(medTests.findByPatientId(1L)).thenReturn(List.of());
		when(certs.findByPatientId(1L)).thenReturn(List.of());

		PatientMedRecord record = DataHelper.getPatientRecord();

		given()
				.body(record)
				.contentType("application/json")
				.when().get("/restrictions/forUser/" + 1)
				.then()
				.statusCode(200)
				.body("title", containsInAnyOrder
						("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15")
				);
	}
}
