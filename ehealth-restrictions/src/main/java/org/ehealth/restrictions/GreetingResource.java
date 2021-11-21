package org.ehealth.restrictions;

import org.ehealth.restrictions.data.Restriction;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
public class GreetingResource {

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello RESTEasy";
	}

	@GET
	@Path("/custom")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Restriction> custom() {
		return Restriction.listAll();
	}

	@POST
	@Path("/custom/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response remove(@PathParam Long id) {
		boolean res = Restriction.deleteById(id);
		return res ? Response.status(Response.Status.ACCEPTED).build() :
					 Response.status(Response.Status.NOT_ACCEPTABLE).build();
	}
}