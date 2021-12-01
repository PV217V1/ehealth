package soa.ehealth.certificates;

import soa.ehealth.certificates.entity.Certificate;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/certificates")
public class CertificateResource {

    @Inject
    CertificateRepository repository;

    @POST
    @Path("/create")
    public Response create(Certificate certificate) {
        Certificate created = repository.createCertificate(certificate);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}/update")
    public Certificate update(@PathParam("id") Long id, Certificate update) {
        Certificate updated = repository.updateCertificate(id, update);
        return updated;
    }

    @DELETE
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = repository.deleteCertificate(id);

        return Response.ok(deleted).build();
    }

    @GET
    public List<Certificate> getCertificates() {
        return Certificate.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getCertificate(@PathParam("id") Long id) {
        Certificate certificate = Certificate.findById(id);

        if (certificate == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Certificate" + id + "not found")
                    .build();
        }

        return Response.ok(certificate).build();
    }

    @GET
    @Path("/forPerson/{id}")
    public Response getForPerson(@PathParam("id") Long id) {
        Certificate certificate = Certificate.findByPersonId(id);

        if (certificate == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Certificate for person with" + id + "not found")
                    .build();
        }

        return Response.ok(certificate).build();
    }
}
