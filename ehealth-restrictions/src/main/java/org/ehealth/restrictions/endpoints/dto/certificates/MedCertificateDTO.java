package org.ehealth.restrictions.endpoints.dto.certificates;

import java.time.LocalDate;

/**
 * Certificate DTO as defined by the <strong>ehealth-certificates</strong> endpoint
 */
public class MedCertificateDTO {

	/**
	 * The database assigned ID
	 */
	public long id;

	/**
	 * The ID of the person this test belongs to
	 */
	public Long personId;

	/**
	 * The type of the vaccine performed
	 */
	public EVaccinationType vaxType;

	/**
	 * The date of the first dose
	 */
	public LocalDate vaxStarted;

	/**
	 * The date of the last dose
	 */
	public LocalDate vaxCompleted;

	/**
	 * How many doses of the {@link EVaccinationType} the person underwent
	 */
	public Integer doses;

	public MedCertificateDTO() { }

	public MedCertificateDTO(long id, Long personId, EVaccinationType vaxType, LocalDate vaxStarted, LocalDate vaxCompleted, Integer doses) {
		this.id = id;
		this.personId = personId;
		this.vaxType = vaxType;
		this.vaxStarted = vaxStarted;
		this.vaxCompleted = vaxCompleted;
		this.doses = doses;
	}
}
