package org.ehealth.restrictions.processing;

import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.endpoints.dto.PatientMedRecord;

import java.util.List;

/**
 * Handler for filtering applicable restrictions
 */
public interface RestrictionProcessor {
	/**
	 * Function to process {@link PatientMedRecord} and provide which restrictions apply for the patient
	 * @param record the patient's testing and certificate record
	 * @return a list of all restriction which apply for the patient
	 */
	List<Restriction> process(PatientMedRecord record);

	/**
	 * Shortcut to return global restrictions which apply to everybody
	 * @return a list of all restriction which apply globally
	 */
	List<Restriction> getGlobalRestrictions();
}
