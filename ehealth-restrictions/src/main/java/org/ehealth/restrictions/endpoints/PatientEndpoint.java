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

@Path("/ehealth")
@RegisterRestClient(configKey = "patient-service-endpoint")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public interface PatientEndpoint {

	@GET
	@Path("")
	List<PatientDTO> listAll();

	@GET
	@Path("{id}")
	PatientDTO findById(@PathParam Long id);
}
