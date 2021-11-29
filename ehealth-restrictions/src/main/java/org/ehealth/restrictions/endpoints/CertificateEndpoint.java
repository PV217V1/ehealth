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

@Path("/certificates")
@RegisterRestClient(configKey = "certificate-service-endpoint")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public interface CertificateEndpoint {

	@GET
	@Path("/forUser/{id}")
	List<MedCertificateDTO> findByPatientId(@PathParam Long id);

	@GET
	@Path("/{id}")
	MedCertificateDTO findById(@PathParam Long id);
}
