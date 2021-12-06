package soa.ehealth.certificates.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;
import soa.ehealth.certificates.enums.EVaccinationType;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Certificate extends PanacheEntity {

    public Long personId;

    public EVaccinationType vaxType;

    public LocalDate vaxStarted;

    public LocalDate vaxCompleted;

    public Integer doses;

    public Certificate() {
    }

    public Certificate(Long personId, EVaccinationType vaxType, LocalDate vaxStarted, LocalDate vaxCompleted, Integer doses) {
        this.personId = personId;
        this.vaxType = vaxType;
        this.vaxStarted = vaxStarted;
        this.vaxCompleted = vaxCompleted;
        this.doses = doses;
    }

    public static Certificate findByPersonId(Long id) {
        return Certificate.find("personId", Sort.descending("vaxStarted"), id).firstResult();
    }

    public void update(Certificate update) {
        if (update.vaxType != null) {
            this.vaxType = update.vaxType;
        }

        if (update.vaxStarted != null) {
            this.vaxStarted = update.vaxStarted;
        }

        if (update.vaxCompleted != null) {
            this.vaxCompleted = update.vaxCompleted;
        }

        if (update.doses != null) {
            this.doses = update.doses;
            if (vaxType != null && doses.equals(vaxType.getNumberOfDoses()) && vaxCompleted == null) {
                this.vaxCompleted = LocalDate.now();
            }
        }
    }
}
