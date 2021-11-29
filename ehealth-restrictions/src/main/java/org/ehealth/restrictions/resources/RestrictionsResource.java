package org.ehealth.restrictions.resources;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.ehealth.restrictions.endpoints.dto.PatientMedRecord;
import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.entities.RestrictionScope;
import org.ehealth.restrictions.endpoints.CertificateEndpoint;
import org.ehealth.restrictions.endpoints.MedTestsEndpoint;
import org.ehealth.restrictions.endpoints.PatientEndpoint;
import org.ehealth.restrictions.endpoints.dto.certificates.MedCertificateDTO;
import org.ehealth.restrictions.endpoints.dto.medtests.MedTestDTO;
import org.ehealth.restrictions.endpoints.dto.patients.PatientDTO;
import org.ehealth.restrictions.processing.RestrictionProcessor;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("restrictions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestrictionsResource {

	@Inject
	@RestClient
	PatientEndpoint patients;

	@Inject
	@RestClient
	CertificateEndpoint certs;

	@Inject
	@RestClient
	MedTestsEndpoint tests;

	@Inject
	RestrictionProcessor restrictionProcessor;

	@POST
	@Path("/create")
	@Transactional
	@Counted(name = "restrictions.createCalls", description = "How many times this endpoint was called.")
	@Timed(name = "restrictions.createDuration", description = "How long does it take to persist a restriction.")
	public Response create(Restriction item) {
		item.persist();
		return Response.accepted(item)
				.status(Response.Status.CREATED)
				.build();
	}

	@GET
	@Path("")
	public Response retrieveAll() {
		return Response.ok(Restriction.listAll())
				.build();
	}

	@GET
	@Path("/scoped/{scope}")
	public Response retrieveAll(@PathParam RestrictionScope scope) {
		return Response.ok(Restriction.find("scope", scope).list())
				.build();
	}

	@GET
	@Path("/{id}")
	public Response retrieve(@PathParam Long id) {
		return Response.ok(Restriction.findById(id))
				.build();
	}

	@PUT
	@Path("/{id}/update")
	public Response update(@PathParam Long id, Restriction newValue) {
		Restriction found = Restriction.findById(id);

		if (found == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		found.setTitle(newValue.getTitle());
		found.setDescription(newValue.getDescription());
		found.setExpired(newValue.getExpired());

		return Response.ok(found).build();
	}


	@GET
	@Path("/forUser/{id}")
	@APIResponses({
			@APIResponse(responseCode = "503",
					description = "Could not contact dependent services, " +
							"cannot evaluate the patient, " +
							"returning Global restrictions only!"),
			@APIResponse(responseCode = "200",
					description = "Returns the descriptions that patient " +
							"with provided certificates and tests must follow!")
	})
	@Fallback(fallbackMethod = "forUserFallback")
	@Counted(name = "restrictions.forUserCalls", description = "How many times this endpoint was called.")
	@Timed(name = "restrictions.forUserDuration", description = "How long does it take to lookup restrictions for users.")
	public Response forUser(@PathParam Long id) {
		PatientDTO p;
		List<MedCertificateDTO> medCerts;
		List<MedTestDTO> medTests;

		try {
			p = patients.findById(id);
		} catch (Exception e) {
			return Response.ok(restrictionProcessor.getGlobalRestrictions()).
					status(Response.Status.SERVICE_UNAVAILABLE)
					.build();
		}

		try {
			medCerts = certs.findByPatientId(id);
		} catch (Exception e) {
			return Response.ok(restrictionProcessor.getGlobalRestrictions()).
					status(Response.Status.SERVICE_UNAVAILABLE)
					.build();
		}

		try {
			medTests = tests.findByPatientId(id);
		} catch (Exception e) {
			return Response.ok(restrictionProcessor.getGlobalRestrictions()).
					status(Response.Status.SERVICE_UNAVAILABLE)
					.build();
		}

		return Response.ok(restrictionProcessor.process(new PatientMedRecord(p, medCerts, medTests)))
				.build();
	}

	@DELETE
	@Path("/{id}/delete")
	@Transactional
	@Counted(name = "restrictions.deleteCalls", description = "How many times this endpoint was called.")
	@Timed(name = "restrictions.deleteDuration", description = "How long does it take to delete a restriction.")
	public Response delete(@PathParam Long id) {
		boolean res = Restriction.deleteById(id);
		return res ? Response.ok().build() : Response.noContent().build();
	}

	// Used as a fallback method
	@SuppressWarnings("unused")
	public Response forUserFallback(Long id) {
		return Response.status(Response.Status.SERVICE_UNAVAILABLE)
				.build();
	}
}