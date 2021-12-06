package soa.ehealth.certificates.services;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import soa.ehealth.certificates.dto.PersonDto;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Contract definition for the ehealth-patients microservice
 */
@Path("/person")
@RegisterRestClient(configKey = "people-service-endpoint")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public interface PersonService {

	/**
	 * Retrieve person by their ID
	 *
	 * @param id the ID of the person
	 * @return the {@link PersonDto} for the person, {@link null} if not found
	 */
	@GET
	@Path("/selectById/{id}")
	PersonDto findById(@PathParam("id") Long id);
}