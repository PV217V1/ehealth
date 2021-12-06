package soa.ehealth.certificates;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import soa.ehealth.certificates.dto.CreateCertificateDto;
import soa.ehealth.certificates.dto.ErrorDto;
import soa.ehealth.certificates.entity.Certificate;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/certificates")
@Produces(MediaType.APPLICATION_JSON)
public class CertificateResource {

    @Inject
    CertificateRepository repository;

    @POST
    @Path("/create")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Inserts the new certificate into a database")
    })
    public Response create(CreateCertificateDto certificate) {
        Certificate created = repository.createCertificate(new Certificate(certificate.personId, certificate.vaxType, certificate.vaxStarted, certificate.vaxCompleted, certificate.doses));
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}/update")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Updates the certificate"),
            @APIResponse(responseCode = "404", description = "Certificate with given id was not found")
    })
    public Response update(@PathParam("id") Long id, Certificate update) {
        Certificate updated;

        try {
            updated = repository.updateCertificate(id, update);
        } catch (NotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorDto(e.getMessage()))
                    .build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}/delete")
    @Produces(MediaType.TEXT_PLAIN)
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
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Returns the certificate"),
            @APIResponse(responseCode = "404", description = "Certificate with given id was not found")
    })
    public Response getCertificate(@PathParam("id") Long id) {
        Certificate certificate = Certificate.findById(id);

        if (certificate == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorDto("Certificate id " + id + " not found"))
                    .build();
        }

        return Response.ok(certificate).build();
    }

    @GET
    @Path("/forPerson/{id}")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Updates the certificate"),
            @APIResponse(responseCode = "404", description = "Certificate for person with given id was not found")
    })
    public Response getForPerson(@PathParam("id") Long id) {
        Certificate certificate = Certificate.findByPersonId(id);

        if (certificate == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorDto("Certificate for person with id " + id + " not found"))
                    .build();
        }

        return Response.ok(certificate).build();
    }
}
