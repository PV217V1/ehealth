package soa.ehealth;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;
import soa.ehealth.tmp.Patient;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("ehealth")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EHealthResource {
	private static final Logger LOGGER = Logger.getLogger(EHealthResource.class.getName());

	@GET
	public Uni<List<Patient>> getPatients() {
		return Patient.listAll();
	}

	@GET
	@Path("{id}")
	public Uni<Patient> getSinglePatent(Long id) {
		return Patient.findById(id).onItem().castTo(Patient.class).onItem().transform(it -> {
			if (it == null) throw new WebApplicationException("Patient with id: " + id + " does not exist.", 404);
			return it;
		});
	}

	@POST
	@Transactional
	public Uni<Response> create(Patient patient) {
		// TODO: Some validation
		return Panache.<Patient>withTransaction(patient::persist).onItem().transform(it ->
				Response.ok(patient).status(201).build());
	}

	@PUT
	@Path("{id}")
	@Transactional
	public Uni<Patient> updatePatient(Long id, Patient patient) {
		if (patient.name == null) {
			throw new WebApplicationException("Patient Name was not set on request.", 422);
		}

		return Patient.findById(id).onItem().transform(it -> {
			if (it == null) {
				throw new WebApplicationException("Patient with id of " + id + " does not exist.", 404);
			}
			Patient p = (Patient) it;
			p.name = patient.name;
			p.age = patient.age;
			p.phoneNum = patient.phoneNum;
			p.address = patient.address;
			return p;
		});
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Uni<Response> delete(Long id) {
		return Patient.findById(id).onItem()
				.transformToUni(it -> Panache.withTransaction(it::delete).onItem()
						.transform(it_d -> {
							if (it != null)
								return Response.status(204).build();
							else
								return Response.status(Response.Status.BAD_REQUEST).build();
						}));
	}
}
