package soa.ehealth.resource;


import soa.ehealth.dto.Person;

import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResouce {
    @Inject
    PeopleRepository peopleRepository;

    @POST
    @Path("/create")
    @Transactional
    public Response newRegistration(Person person){
        peopleRepository.persist(person);
        return  Response.created(URI.create("/person" + person.getName())).build();
    }
    @GET
    @Path("/selectAll")
    public List<Person> list() {
        return peopleRepository.listAll();
    }

    @PUT
    @Path("/{id}/update")
    @Transactional
    public Person updatePatientIn(@PathParam("id") Long id, Person person) {
        Person entity = peopleRepository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        entity.setName(person.getName());
        entity.setAge(person.getAge() );
        entity.setAddress(person.getAddress());
        entity.setTelephone(person.getTelephone());
        return entity;
    }
    @DELETE
    @Path("/{id}/delete")
    @Transactional
    public void deletePatient(@PathParam("id") Long id) {
        Person entity = peopleRepository.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        peopleRepository.delete(entity);
    }
    @GET
    @Path("/selectById/{id}")
    public Person search(@PathParam("id") Long id) {
        return peopleRepository.findById(id);
    }

    @GET
    @Path("totalCount/count")
    public Long count() {
        return peopleRepository.count();
    }
}
