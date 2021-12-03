package org.ehealth.restrictions.resources;

import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.ehealth.restrictions.endpoints.dto.PersonMedRecord;
import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.entities.RestrictionScope;
import org.ehealth.restrictions.endpoints.CertificateEndpoint;
import org.ehealth.restrictions.endpoints.MedTestsEndpoint;
import org.ehealth.restrictions.endpoints.PeopleEndpoint;
import org.ehealth.restrictions.endpoints.dto.certificates.MedCertificateDTO;
import org.ehealth.restrictions.endpoints.dto.medtests.MedTestDTO;
import org.ehealth.restrictions.endpoints.dto.people.PersonDTO;
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
import java.time.LocalDate;
import java.util.List;

/**
 * Endpoint for this microservice
 */
@Path("restrictions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestrictionsResource {

	@Inject
	@RestClient
	PeopleEndpoint people;

	@Inject
	@RestClient
	CertificateEndpoint certs;

	@Inject
	@RestClient
	MedTestsEndpoint tests;

	@Inject
	RestrictionProcessor restrictionProcessor;

	MeterRegistry registry;

	public RestrictionsResource(MeterRegistry registry) {
		this.registry = registry;
	}

	/**
	 * <strong>C</strong>RUD operation
	 *
	 * @param item the {@link Restriction} to introduce
	 * @return 201 on success
	 */
	@POST
	@Path("/create")
	@Transactional
	@Timed(name = "restrictions.createDuration", description = "How long does it take to persist a restriction.")
	public Response create(Restriction item) {
		item.persist();
		return Response.accepted(item)
				.status(Response.Status.CREATED)
				.build();
	}

	/**
	 * C<strong>R</strong>UD operation for all {@link Restriction}
	 *
	 * @return 200 on success
	 */
	@GET
	@Path("")
	public Response retrieveAll() {
		return Response.ok(Restriction.listAll())
				.build();
	}

	/**
	 * C<strong>R</strong>UD operation for all {@link Restriction}s of a given scope
	 *
	 * @param scope the scope to filter by
	 * @return 200 on success
	 */
	@GET
	@Path("/scoped/{scope}")
	public Response retrieveAll(@PathParam RestrictionScope scope) {
		return Response.ok(Restriction.find("scope", scope).list())
				.build();
	}

	/**
	 * C<strong>R</strong>UD operation for single {@link Restriction}
	 *
	 * @param id the identifier of the {@link Restriction}
	 * @return 200 on success
	 */
	@GET
	@Path("/{id}")
	public Response retrieve(@PathParam Long id) {
		return Response.ok(Restriction.findById(id))
				.build();
	}

	/**
	 * CR<strong>U</strong>D operation
	 *
	 * @param id       the identifier of the {@link Restriction} to update
	 * @param newValue the new values to store
	 * @return 200 on success
	 */
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

	/**
	 * Function to return all {@link Restriction}s for given person's medical record
	 *
	 * @param id the parson's identifier
	 * @return 200 on success
	 */
	@GET
	@Path("/forUser/{id}")
	@APIResponses({
			@APIResponse(responseCode = "503",
					description = "Could not contact dependent services, " +
							"cannot evaluate the person, " +
							"returning Global restrictions only!"),
			@APIResponse(responseCode = "200",
					description = "Returns the restrictions that person " +
							"with provided certificates and tests must follow!")
	})
	@Fallback(fallbackMethod = "forPersonFallback")
	@Timed(name = "restrictions.forPersonDuration", description = "How long does it take to lookup restrictions for people.")
	public Response forPerson(@PathParam Long id) {
		PersonDTO p;
		List<MedCertificateDTO> medCerts;
		List<MedTestDTO> medTests;

		try {
			p = people.findById(id);
		} catch (Exception e) {
			Log.error(e);
			return Response.ok(restrictionProcessor.getGlobalRestrictions()).
					status(Response.Status.SERVICE_UNAVAILABLE)
					.build();
		}

		try {
			medCerts = certs.findByPersonId(id);
		} catch (Exception e) {
			Log.error(e);
			return Response.ok(restrictionProcessor.getGlobalRestrictions()).
					status(Response.Status.SERVICE_UNAVAILABLE)
					.build();
		}

		try {
			medTests = tests.findByPatientId(id);
		} catch (Exception e) {
			Log.error(e);
			return Response.ok(restrictionProcessor.getGlobalRestrictions()).
					status(Response.Status.SERVICE_UNAVAILABLE)
					.build();
		}

		if (medCerts.size() > 0) {
			registry.counter("withCerts").increment();
		}
		if (medTests.size() > 0) {
			registry.counter("withMedTests").increment();
		}

		return Response.ok(restrictionProcessor.process(new PersonMedRecord(p, medCerts, medTests)))
				.build();
	}

	/**
	 * Function to return all {@link Restriction}s for given date
	 *
	 * @param date the date to find applicable restrictions for
	 * @return 200 on success
	 */
	@GET
	@Path("/forDate/{date}")
	@Parameter(name = "date", description = "The date in yyyy-mm-dd format", example = "2021-08-22")
	@Timed(name = "restrictions.forDateDuration", description = "How long does it take to lookup restrictions for dates.")
	public Response forDate(@PathParam LocalDate date) {
		return Response.ok(restrictionProcessor.getRestrictionsForDate(date)).build();
	}

	/**
	 * CRU<strong>D</strong> operation
	 *
	 * @param id the identifier of the {@link Restriction} to delete
	 * @return 200 on success
	 */
	@DELETE
	@Path("/{id}/delete")
	@Transactional
	@Timed(name = "restrictions.deleteDuration", description = "How long does it take to delete a restriction.")
	public Response delete(@PathParam Long id) {
		boolean res = Restriction.deleteById(id);
		return res ? Response.ok().build() : Response.noContent().build();
	}

	// Used as a fallback method
	@SuppressWarnings("unused")
	public Response forPersonFallback(Long id) {
		return Response.status(Response.Status.SERVICE_UNAVAILABLE)
				.build();
	}
}