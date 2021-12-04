package soa.ehealth.medtests;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soa.ehealth.medtests.endpoints.PersonDTO;
import soa.ehealth.medtests.endpoints.PersonEndpoint;
import soa.ehealth.medtests.entities.MedTest;
import soa.ehealth.medtests.entities.TestType;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
@Transactional
public class MedTestsResourceTest {

	@BeforeEach
	void setup() {
		MedTest.listAll().await().indefinitely();
		MedTest.deleteAll().await().indefinitely();
		MedTest.persist(DataHelper.getMedTests()).await().indefinitely();
		MedTest.listAll().await().indefinitely();
	}

	@Test
	public void create() {
		MedTest r = new MedTest(1L,
				LocalDate.of(2021, Month.AUGUST, 22),
				604800,
				TestType.PCR);

		given()
				.body(r)
				.contentType("application/json")
				.when().post("/medtests/create")
				.then()
				.statusCode(201)
				.body("testType", containsString("PCR"))
				.body("validThroughSeconds", is(604800));
	}

	@Test
	public void retrieveAll() {
		given()
				.when().get("/medtests")
				.then()
				.statusCode(200)
				.body("size()", is(15));
	}

	@Test
	public void retrievePCRAll() {
		given()
				.when().get("/medtests/testType/PCR")
				.then()
				.statusCode(200)
				.body("size()", is(8));
	}

	@InjectMock
	@RestClient
	PersonEndpoint people;

	@Test
	public void retrieveForUserFail() {
		when(people.findById(9999L)).thenReturn(Uni.createFrom().item((PersonDTO) null));

		given()
				.when().get("/medtests/forPerson/9999")
				.then()
				.statusCode(404);
	}

	@Test
	public void retrieveForUser3() {
		when(people.findById(3L)).thenReturn(Uni.createFrom().item(new PersonDTO(3L,"","","","")));

		given()
				.when().get("/medtests/forPerson/3")
				.then()
				.statusCode(200)
				.body("size()", is(3));
	}
}
