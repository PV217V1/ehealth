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

	/**
	 * Endpoint to retrieve person by their database identifier
	 *
	 * @param id the DB assigned identifier
	 * @return the {@link PersonDTO} for the person, {@link null} if not found
	 */
	@GET
	@Path("/selectById/{id}")
	Uni<PersonDTO> findById(@PathParam("id") Long id);
}