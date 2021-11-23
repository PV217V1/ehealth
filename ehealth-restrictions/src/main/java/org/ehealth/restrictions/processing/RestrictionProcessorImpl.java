package org.ehealth.restrictions.processing;

import org.ehealth.restrictions.entities.RestrictionScope;
import org.jetbrains.annotations.NotNull;
import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.endpoints.dto.PatientMedRecord;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RestrictionProcessorImpl implements RestrictionProcessor {

	@Override
	@NotNull
	public List<Restriction> process(PatientMedRecord record) {
		if (record.certificates.isEmpty() && record.tests.isEmpty()) {
			return Restriction.find("u.scope = @global or u.scope = @nonvax",
					Map.of("global", RestrictionScope.GLOBAL,
							"nonvax", RestrictionScope.NOT_VACCINATED))
					.list();
		}
		return List.of();
	}

	@Override
	public List<Restriction> getGlobalRestrictions() {
		return Restriction.find("u.scope = @global",
				Map.of("global", RestrictionScope.GLOBAL))
				.list();
	}
}
