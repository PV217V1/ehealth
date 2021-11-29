package soa.ehealth.resource;


import soa.ehealth.dto.People;

import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




@Path("/people_resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleResouce {
    @Inject
    PeopleRepository peopleRepository;

    @POST
    @Path("/registerpeople")
    @Transactional
    public Response newRegistration(People patient){
        peopleRepository.persist(patient);
        return  Response.created(URI.create("/patient" + patient.getName())).build();
    }
    @GET
    @Path("/getpeopleinfo")
    public List<People> list() {
        return peopleRepository.listAll();
    }

    @PUT
    @Path("/updatepeopleinfo/{id}")
    @Transactional
    public People updatePatientIn(@PathParam("id") Long id, People patient) {
        People entity = peopleRepository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        entity.setName(patient.getName());
        entity.setAge(patient.getAge() );
        entity.setAddress(patient.getAddress());
        entity.setTelephone(patient.getTelephone());
        return entity;
    }
    @DELETE
    @Path("/deletepeoplebyid/{id}")
    @Transactional
    public void deletePatient(@PathParam("id") Long id) {
        People entity = peopleRepository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        peopleRepository.delete(entity);
    }
    @GET
    @Path("/findpeoplebyid/{id}")
    public People search(@PathParam("id") Long id) {
        return peopleRepository.findById(id);
    }

    @GET
    @Path("totalPatient/count")
    public Long count() {
        return peopleRepository.count();
    }
}
