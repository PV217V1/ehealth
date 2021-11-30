package org.ehealth.restrictions.endpoints;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.ehealth.restrictions.endpoints.dto.medtests.MedTestDTO;
import org.ehealth.restrictions.endpoints.dto.patients.PatientDTO;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Contract definition for the ehealth-medtests microservice
 */
@Path("/certificates")
@RegisterRestClient(configKey = "tests-service-endpoint")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public interface MedTestsEndpoint {

    /**
     * Endpoint to retrieve all medical test for given patient
     *
     * @param id the unique identifier of a patient
     * @return list of all medical tests of the patient
     */
    @GET
    @Path("/forUser/{id}")
    List<MedTestDTO> findByPatientId(@PathParam Long id);

	/**
	 * Endpoint to retrieve medical test by its database identifier
	 *
	 * @param id the DB assigned identifier
	 * @return the {@link MedTestDTO} for the medical test, {@link null} if not found
	 */
    @GET
    @Path("/{id}")
    MedTestDTO findById(@PathParam Long id);
}
