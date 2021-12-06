package soa.ehealth.certificates.dto;

import soa.ehealth.certificates.enums.EVaccinationType;

import java.time.LocalDate;

public class CreateCertificateDto {

    /**
     * ID of vaccinated person
     */
    public Long personId;

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

    public CreateCertificateDto() {
    }

    public CreateCertificateDto(Long personId, EVaccinationType vaxType, LocalDate vaxStarted, LocalDate vaxCompleted, Integer doses) {
        this.personId = personId;
        this.vaxType = vaxType;
        this.vaxStarted = vaxStarted;
        this.vaxCompleted = vaxCompleted;
        this.doses = doses;
    }
}
