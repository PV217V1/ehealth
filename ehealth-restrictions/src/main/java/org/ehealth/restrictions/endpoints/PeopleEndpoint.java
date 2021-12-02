package org.ehealth.restrictions.endpoints;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.ehealth.restrictions.endpoints.dto.people.PersonDTO;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Contract definition for the ehealth-people microservice
 */
@Path("/person")
@RegisterRestClient(configKey = "people-service-endpoint")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public interface PeopleEndpoint {

    /**
     * Endpoint to retrieve all people registered into the system
     *
     * @return list of all people
     */
    @GET
    @Path("/selectAll")
    List<PersonDTO> listAll();

    /**
     * Endpoint to retrieve person by their database identifier
     *
     * @param id the DB assigned identifier
     * @return the {@link PersonDTO} for the person, {@link null} if not found
     */
    @GET
    @Path("/selectById/{id}")
    PersonDTO findById(@PathParam Long id);
}
