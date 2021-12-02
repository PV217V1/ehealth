package soa.ehealth.medtests.endpoints;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/person")
@RegisterRestClient(configKey = "patient-service-endpoint")
@Produces(MediaType.APPLICATION_JSON)
public interface PersonEndpoint {

	@GET
	@Path("")
	List<PersonDTO> listAll();

	@GET
	@Path("{id}")
	PersonDTO findById(@PathParam Long id);
}