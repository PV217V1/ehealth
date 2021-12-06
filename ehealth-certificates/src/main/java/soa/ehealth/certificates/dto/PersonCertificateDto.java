package soa.ehealth.certificates.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import soa.ehealth.certificates.entities.Certificate;
import soa.ehealth.certificates.enums.EVaccinationType;

import java.time.LocalDate;

@Schema
/**
 * DTO that combines data of certificate and person
 */
public class PersonCertificateDto {

    /**
     * Name of the person
     */
    public String name;

    /**
     * Age of the person
     */
    public String age;

    /**
     * Sex of the person
     */
    public String sex;

    /**
     * ID of the person
     */
    public Long personId;

    /**
     * ID of the certificate
     */
    public Long id;

    /**
     * Type of vaccine
     */
    public EVaccinationType vaxType;

    /**
     * Date when first dose was administered
     */
    public LocalDate vaxStarted;

    /**
     * Date when final dose was administered
     */
    public LocalDate vaxCompleted;

    /**
     * Number of doses administered
     */
    public Integer doses;

    public PersonCertificateDto() {
    }

    public PersonCertificateDto(PersonDto p, Certificate c) {
        this.name = p.name;
        this.age = p.age;
        this.sex = p.sex;
        this.personId = c.personId;
        this.id = c.id;
        this.vaxType = c.vaxType;
        this.vaxStarted = c.vaxStarted;
        this.vaxCompleted = c.vaxCompleted;
        this.doses = c.doses;
    }
}
