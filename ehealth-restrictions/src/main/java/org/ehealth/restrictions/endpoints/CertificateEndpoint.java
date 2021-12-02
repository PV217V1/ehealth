package org.ehealth.restrictions.endpoints;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.ehealth.restrictions.endpoints.dto.certificates.MedCertificateDTO;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Contract definition for the ehealth-certificates microservice
 */
@Path("/certificates")
@RegisterRestClient(configKey = "certificates-service-endpoint")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public interface CertificateEndpoint {

	/**
	 * Endpoint to retrieve all certificates for given patient
	 *
	 * @param id the unique identifier of a patient
	 * @return list of all certificates issued to the patient
	 */
	@GET
	@Path("/forUser/{id}")
	List<MedCertificateDTO> findByPatientId(@PathParam Long id);

	/**
	 * Endpoint to retrieve certificate by its database identifier
	 *
	 * @param id the DB assigned identifier
	 * @return the {@link MedCertificateDTO} for the certificate, {@link null} if not found
	 */
	@GET
	@Path("/{id}")
	MedCertificateDTO findById(@PathParam Long id);
}
