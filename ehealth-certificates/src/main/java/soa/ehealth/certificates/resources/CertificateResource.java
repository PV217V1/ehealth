package soa.ehealth.certificates.resources;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import soa.ehealth.certificates.dto.CreateCertificateDto;
import soa.ehealth.certificates.dto.ErrorDto;
import soa.ehealth.certificates.dto.PersonCertificateDto;
import soa.ehealth.certificates.dto.PersonDto;
import soa.ehealth.certificates.entities.Certificate;
import soa.ehealth.certificates.services.CertificateService;
import soa.ehealth.certificates.services.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/certificates")
@Produces(MediaType.APPLICATION_JSON)
public class CertificateResource {

    @Inject
    CertificateService certificateService;

    @Inject
    @RestClient
    PersonService personService;

    /**
     * Creates new certificate
     *
     * @param certificate the {@link CreateCertificateDto} to persist
     * @return 201 on success
     */
    @POST
    @Path("/create")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Inserts the new certificate into a database")
    })
    @Timed(name = "certificates.createDuration", description = "How long it takes to persist a certificate.")
    @Counted(name = "certificates.createCalls", description = "How many times a certificate was created.")
    public Response create(CreateCertificateDto certificate) {
        Certificate created = certificateService.createCertificate(new Certificate(certificate.personId, certificate.vaxType, certificate.vaxStarted, certificate.vaxCompleted, certificate.doses));
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    /**
     * Updates certificate
     *
     * @param id the ID of the certificate to be updated
     * @param update the new data of the {@link Certificate}
     * @return 200 on success, 404 if the certificate does not exist
     */
    @PUT
    @Path("/{id}/update")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Updates the certificate"),
            @APIResponse(responseCode = "404", description = "Certificate with given id was not found")
    })
    @Timed(name = "certificates.updateDuration", description = "How long it takes to update a certificate.")
    public Response update(@PathParam("id") Long id, Certificate update) {
        Certificate updated;

        try {
            updated = certificateService.updateCertificate(id, update);
        } catch (NotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorDto(e.getMessage()))
                    .build();
        }

        return Response.ok(updated).build();
    }

    /**
     * Deletes certificate
     *
     * @param id the ID of the certificate to be deleted
     * @return 200 "true" on success, 200 "false" if the certificate was not deleted
     */
    @DELETE
    @Path("/{id}/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Timed(name = "certificates.deleteDuration", description = "How long it takes to delete a certificate.")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = certificateService.deleteCertificate(id);

        return Response.ok(deleted).build();
    }

    /**
     * Retrieves all certificates
     *
     * @return 200 on success
     */
    @GET
    public List<Certificate> getCertificates() {
        return Certificate.listAll();
    }

    /**
     * Retrieves certificate by its ID
     *
     * @param id the ID of the certificate to be returned
     * @return 200 on success, 404 if the certificate does not exist
     */
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

    /**
     * Retrieves certificate with data about its owner ({@link PersonCertificateDto}) based on the ID of the person to whom the certificate belongs
     *
     * @param id the ID of the person
     * @return 200 on success, 404 if the certificate or person does not exist
     */
    @GET
    @Path("/forPerson/{id}")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Returns the certificate, with info about person (PersonCertificateDto)"),
            @APIResponse(responseCode = "404", description = "Certificate or person was not found")
    })
    @Timed(name = "certificates.getForPersonDuration", description = "How long it takes to retrieve a certificate for specific person.")
    @Counted(name = "certificates.getForPersonCalls", description = "How many times this endpoint was called.")
    public Response getForPerson(@PathParam("id") Long id) {
        Certificate certificate = Certificate.findByPersonId(id);
        PersonDto person = personService.findById(id);

        if (certificate == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorDto("Certificate for person with id " + id + " not found"))
                    .build();
        }

        if (person == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorDto("Person with id " + id + " not found"))
                    .build();
        }

        return Response.ok(new PersonCertificateDto(person, certificate)).build();
    }
}
