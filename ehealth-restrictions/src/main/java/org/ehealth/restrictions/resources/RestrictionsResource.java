package org.ehealth.restrictions.resources;

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
	public Restriction create(Restriction item) {
		item.persist();
		return item;
	}

	@GET
	@Path("")
	public List<Restriction> retrieveAll() {
		return Restriction.listAll();
	}

	@GET
	@Path("/scoped/{scope}")
	public List<Restriction> retrieveAll(@PathParam RestrictionScope scope) {
		return Restriction.find("scope", scope).list();
	}

	@GET
	@Path("/{id}")
	public Restriction retrieve(@PathParam Long id) {
		return Restriction.findById(id);
	}


	@GET
	@Path("/forUser/{id}")
	public List<Restriction> forUser(@PathParam Long id) {
		PatientDTO p = patients.findById(id);
		List<MedCertificateDTO> medCerts = certs.findByPatientId(id);
		List<MedTestDTO> medTests = tests.findByPatientId(id);
		return restrictionProcessor.process(new PatientMedRecord(p, medCerts, medTests));
	}

	@DELETE
	@Path("delete")
	@Transactional
	public Response delete(Long id) {
		boolean res = Restriction.deleteById(id);
		return res ? Response.ok().build() : Response.noContent().build();
	}
}