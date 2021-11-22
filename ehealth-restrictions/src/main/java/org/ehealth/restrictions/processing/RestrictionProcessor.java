package org.ehealth.restrictions.processing;

import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.endpoints.dto.PatientMedRecord;

import java.util.List;

public interface RestrictionProcessor {
	List<Restriction> process(PatientMedRecord record);
}
