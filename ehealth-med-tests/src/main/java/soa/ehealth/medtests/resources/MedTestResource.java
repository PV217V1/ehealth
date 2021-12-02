package soa.ehealth.medtests.resources;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import soa.ehealth.medtests.endpoints.PersonDTO;
import soa.ehealth.medtests.endpoints.PersonEndpoint;
import soa.ehealth.medtests.entities.MedTest;
import soa.ehealth.medtests.entities.TestType;

/**
 * Endpoint for this microservice
 */
@Path("medtests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedTestResource {

	@Inject
	@RestClient
	PersonEndpoint people;

	/**
	 * <strong>C</strong>RUD operation
	 *
	 * @param item the {@link MedTest} to introduce
	 * @return 201 on success
	 */
	@POST
	@Path("/create")
	@Transactional
	@Counted(name = "medtests.createCalls", description = "How many times this endpoint was called.")
	@Timed(name = "medtests.createDuration", description = "How long does it take to persist a medical test.")
	public Uni<Response> create(MedTest item) {
		return item.persist().onItem()
				.transform(c -> Response.ok(c).status(Response.Status.CREATED).build());
	}

	/**
	 * C<strong>R</strong>UD operation for all {@link MedTest}
	 *
	 * @return 200 on success
	 */
	@GET
	@Path("")
	public Uni<Response> retrieveAll() {
		return MedTest.listAll().onItem()
				.transform(item -> Response.ok(item).build());
	}

	/**
	 * C<strong>R</strong>UD operation for all {@link MedTest}s of a given method
	 *
	 * @param type the type to filter by
	 * @return 200 on success
	 */
	@GET
	@Path("/testType/{type}")
	public Uni<Response> retrieveAll(@PathParam("type") TestType type) {
		return MedTest.find("testType", type).list().onItem()
				.transform(item -> Response.ok(item).build());
	}

	/**
	 * C<strong>R</strong>UD operation for single {@link MedTest}
	 *
	 * @param id the identifier of the {@link MedTest}
	 * @return 200 on success
	 */
	@GET
	@Path("/{id}")
	public Uni<Response> retrieve(@PathParam("id") Long id) {
		return MedTest.findById(id).onItem()
				.transform(item -> Response.ok(item).build());
	}

	/**
	 * Function to return all {@link MedTest}s for a Person by their ID
	 *
	 * @param id the patient's identifier
	 * @return 200 on success
	 */
	@APIResponses({
			@APIResponse(responseCode = "503",
					description = "Could not contact dependent services in time, " +
							"cannot retrieve the tests!"),
			@APIResponse(responseCode = "200",
					description = "Returns all the medical tests that person conducted.")
	})
	@GET
	@Path("/forPerson/{id}")
	@Counted(name = "medtests.forPersonCalls", description = "How many times this endpoint was called.")
	@Timed(name = "medtests.forPersonDuration", description = "How long does it take to retrieve all tests for a person.")
	@Fallback(fallbackMethod = "forPerson")
	public Uni<Response> forPerson(@PathParam("id") Long id) {
		Uni<PersonDTO> p = people.findById(id);

		return p.onItem().transformToUni(item -> {
			if (item != null && item.id.equals(id)) {
				return MedTest.find("personId", id).list().onItem()
						.transform(itemInner -> Response.ok(itemInner).build());
			}
			return Uni.createFrom().item(Response.status(Response.Status.NOT_FOUND).build());
		});
	}

	/**
	 * CRU<strong>D</strong> operation
	 *
	 * @param id the identifier of the {@link MedTest} to delete
	 * @return 200 on success
	 */
	@DELETE
	@Path("delete")
	@Transactional
	public Uni<Response> delete(@QueryParam("id") Long id) {
		return MedTest.deleteById(id).onItem()
				.transform(item -> item ? Response.ok().build() : Response.notModified().build());
	}

	// Used as a fallback method
	@SuppressWarnings("unused")
	public Response forPersonFallback(Long id) {
		return Response.status(Response.Status.SERVICE_UNAVAILABLE)
				.build();
	}
}
