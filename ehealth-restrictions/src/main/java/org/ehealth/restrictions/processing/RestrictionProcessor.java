package org.ehealth.restrictions.processing;

import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.endpoints.dto.PersonMedRecord;

import java.time.LocalDate;
import java.util.List;

/**
 * Handler for filtering applicable restrictions
 */
public interface RestrictionProcessor {
	/**
	 * Function to process {@link PersonMedRecord} and provide which restrictions apply for the person
	 * @param record the person's testing and certificate record
	 * @return a list of all restriction which apply for the person
	 */
	List<Restriction> process(PersonMedRecord record);

	/**
	 * Shortcut to return global restrictions which apply to everybody
	 * @return a list of all restriction which apply globally
	 */
	List<Restriction> getGlobalRestrictions();

	/**
	 * Function to filter out restrictions that are valid for the set date
	 * @param date the date to filter by
	 * @return a list of all restriction which apply for the date
	 */
    List<Restriction> getRestrictionsForDate(LocalDate date);
}
