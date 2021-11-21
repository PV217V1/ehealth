package org.ehealth.restrictions

import org.ehealth.restriction.data.Restriction
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("")
class GreetingResource {

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	fun hello(): String {
		return "Hello RESTEasy"
	}

	@GET
	@Path("/custom")
	@Produces(MediaType.APPLICATION_JSON)
	fun custom(): Restriction {
		val ret = Restriction()
		ret.title = "Test"
		return ret;
	}
}