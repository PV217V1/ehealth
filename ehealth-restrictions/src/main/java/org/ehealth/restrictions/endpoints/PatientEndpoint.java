package org.ehealth.restrictions.endpoints;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.ehealth.restrictions.endpoints.dto.patients.PatientDTO;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Contract definition for the ehealth-patients microservice
 */
@Path("/person")
@RegisterRestClient(configKey = "patient-service-endpoint")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public interface PatientEndpoint {

    /**
     * Endpoint to retrieve all patients registered into the system
     *
     * @return list of all patients
     */
    @GET
    @Path("/selectAll")
    List<PatientDTO> listAll();

    /**
     * Endpoint to retrieve patient by their database identifier
     *
     * @param id the DB assigned identifier
     * @return the {@link PatientDTO} for the patient, {@link null} if not found
     */
    @GET
    @Path("/selectById/{id}")
    PatientDTO findById(@PathParam Long id);
}
