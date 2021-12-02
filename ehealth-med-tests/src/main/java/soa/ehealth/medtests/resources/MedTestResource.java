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
import org.eclipse.microprofile.rest.client.inject.RestClient;
import soa.ehealth.medtests.endpoints.PersonDTO;
import soa.ehealth.medtests.endpoints.PersonEndpoint;
import soa.ehealth.medtests.entities.MedTest;
import soa.ehealth.medtests.entities.TestType;

@Path("medtests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedTestResource {

	@Inject
	@RestClient
	PersonEndpoint people;

	@POST
	@Path("/create")
	@Transactional
	public Uni<Response> create(MedTest item) {
		return item.persist().onItem()
				.transform(c -> Response.ok(c).status(Response.Status.CREATED).build());
	}

	@GET
	@Path("")
	public Uni<Response> retrieveAll() {
		return MedTest.listAll().onItem()
				.transform(item -> Response.ok(item).build());
	}

	@GET
	@Path("/testType/{type}")
	public Uni<Response> retrieveAll(@PathParam("type") TestType type) {
		return MedTest.find("testType", type).list().onItem()
				.transform(item -> Response.ok(item).build());
	}

	@GET
	@Path("/{id}")
	public Uni<Response> retrieve(@PathParam("id") Long id) {
		return MedTest.findById(id).onItem()
				.transform(item -> Response.ok(item).build());
	}


	@GET
	@Path("/forPerson/{id}")
	@Fallback(fallbackMethod = "forUserFallback")
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

	@DELETE
	@Path("delete")
	@Transactional
	public Uni<Response> delete(@QueryParam("id") Long id) {
		return MedTest.deleteById(id).onItem()
				.transform(item -> item ? Response.ok().build() : Response.notModified().build());
	}

	// Used as a fallback method
	@SuppressWarnings("unused")
	public Response forUserFallback(Long id) {
		return Response.status(Response.Status.SERVICE_UNAVAILABLE)
				.build();
	}
}
