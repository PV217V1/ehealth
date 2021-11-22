package org.ehealth.restrictions.endpoints;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.ehealth.restrictions.endpoints.dto.medtests.MedTestDTO;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/certificates")
@RegisterRestClient(configKey = "tests-service-endpoint")
@Produces(MediaType.APPLICATION_JSON)
public interface MedTestsEndpoint {

	@GET
	@Path("/forUser/{id}")
	List<MedTestDTO> findByPatientId(@PathParam Long id);

	@GET
	@Path("/{id}")
	MedTestDTO findById(@PathParam Long id);
}
