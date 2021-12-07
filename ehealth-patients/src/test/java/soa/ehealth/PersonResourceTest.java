package soa.ehealth;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import soa.ehealth.dto.Person;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@Transactional
public class PersonResourceTest {

    @Test
    public void testCreate() {
        Person p = new Person("John Smith", "25", "M", "Some address 1", "987654321", "mail@mail.cz");

        given()
                .body(p)
                .contentType("application/json")
                .when().post("/person/create")
                .then()
                .statusCode(201);
    }

    @Test
    public void testSelect() {
        given()
                .when().get("/person/selectById/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void testSelectAllAndCount() {
        given()
                .when().get("/person/selectAll")
                .then()
                .statusCode(200)
                .body("size()", is(5))
                .body("age", containsInAnyOrder("2", "8", "10", "60", "99"));

        given()
                .when().get("/person/count")
                .then()
                .statusCode(200)
                .body(equalTo("5"));
    }

    @Test
    public void testGetAndUpdate() {
        Person p = given()
                .when().get("/person/selectById/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .extract().as(Person.class);

        p.email = "new@email.com";

        given()
                .body(p)
                .contentType("application/json")
                .when().put("/person/1/update")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }
}
