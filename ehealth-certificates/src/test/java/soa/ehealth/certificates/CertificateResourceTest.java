package soa.ehealth.certificates;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import soa.ehealth.certificates.dto.CreateCertificateDto;
import soa.ehealth.certificates.entities.Certificate;
import soa.ehealth.certificates.enums.EVaccinationType;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@Transactional
public class CertificateResourceTest {

    @Test
    public void testCreateAndDelete() {
        LocalDate dateVax = LocalDate.of(2021, 12, 2);
        CreateCertificateDto cert = new CreateCertificateDto(2L, EVaccinationType.JANSSEN, dateVax, dateVax, 1);

        Object id = given()
                .body(cert)
                .contentType("application/json")
                .when().post("/certificates/create")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("vaxType", equalTo("JANSSEN"))
                .body("vaxStarted", equalTo("2021-12-02"))
                .body("vaxCompleted", equalTo("2021-12-02"))
                .body("doses", equalTo(1))
                .extract().path("id");

        given()
                .when().delete("/certificates/" + id + "/delete")
                .then()
                .statusCode(200)
                .body(containsString("true"));
    }

    @Test
    public void testGetCertificate() {
        given()
                .when().get("/certificates/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void testGetNonexistingCertificate() {
        given()
                .when().get("/certificates/10")
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetAll() {
        given()
                .when().get("/certificates")
                .then()
                .statusCode(200)
                .body("size()", is(2))
                .body("vaxType", containsInAnyOrder("JANSSEN", "COMIRNATY"));
    }

    @Test
    public void testGetForPerson() {
        given()
                .when().get("/certificates/forPerson/2")
                .then()
                .statusCode(200)
                .body("personId", equalTo(2));
    }

    @Test
    public void testGetForNonexistingPerson() {
        given()
                .when().get("/certificates/forPerson/12")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteNonexisting() {
        given()
                .when().delete("/certificates/15/delete")
                .then()
                .statusCode(200)
                .body(equalTo("false"));
    }

    @Test
    public void testGetAndUpdate() {
        Certificate cert = given()
                .when().get("/certificates/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .extract().as(Certificate.class);

        cert.vaxCompleted = LocalDate.of(2021, 12, 2);
        cert.doses = 2;

        given()
                .body(cert)
                .contentType("application/json")
                .when().put("/certificates/1/update")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("vaxType", equalTo("COMIRNATY"))
                .body("vaxStarted", equalTo("2021-11-14"))
                .body("vaxCompleted", equalTo("2021-12-02"))
                .body("doses", equalTo(2));
    }

    @Test
    public void testUpdateNonexisting() {
        LocalDate dateVax = LocalDate.of(2021, 12, 2);
        Certificate cert = new Certificate(1L, EVaccinationType.JANSSEN, dateVax, dateVax, 1);

        given()
                .body(cert)
                .contentType("application/json")
                .when().put("/certificates/17/update")
                .then()
                .statusCode(404);
    }
}
