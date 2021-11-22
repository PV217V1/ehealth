package org.ehealth.restrictions.processing;

import org.jetbrains.annotations.NotNull;
import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.endpoints.dto.PatientMedRecord;

import java.util.List;

public class RestrictionProcessorImpl implements RestrictionProcessor {
	@Override
	@NotNull
	public List<Restriction> process(PatientMedRecord record) {
		return List.of();
	}
}
