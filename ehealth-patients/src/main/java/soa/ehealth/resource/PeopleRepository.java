package soa.ehealth.resource;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import soa.ehealth.dto.Person;

import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class PeopleRepository implements PanacheRepository<Person> //PanacheRepository<Patient>
{
    /*public Patient findByName(String name){
        return find("patientName", name).firstResult();
    }

    public List<Patient> findTel(String tell){
        return find("patientTel LIKE ", tell).singleResult() ;
    }
    public List<Patient> findOrdered() {
        return find("ORDER BY patientAddress").list();
    }*/
}