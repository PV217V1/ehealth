package soa.ehealth.medtests.endpoints;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Contract definition for the ehealth-people microservice
 */
@Path("/person")
@RegisterRestClient(configKey = "people-service-endpoint")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public interface PersonEndpoint {

	@GET
	@Path("/{id}")
	Uni<PersonDTO> findById(@PathParam("id") Long id);
}