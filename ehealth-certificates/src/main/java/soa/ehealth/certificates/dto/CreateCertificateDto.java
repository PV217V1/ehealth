package soa.ehealth.certificates.dto;

import soa.ehealth.certificates.enums.EVaccinationType;

import java.time.LocalDate;

public class CreateCertificateDto {

    public Long personId;

    public EVaccinationType vaxType;

    public LocalDate vaxStarted;

    public LocalDate vaxCompleted;

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
