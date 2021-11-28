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
		if(!record.certificates.isEmpty()){
			return Restriction.find("scope = :scope1 or scope = :scope2",
					Map.of("scope1", RestrictionScope.GLOBAL, "scope2", RestrictionScope.VACCINATED))
					.list();
		}
		return List.of();
	}

	@Override
	public List<Restriction> getGlobalRestrictions() {
		return Restriction.find("scope = :scope",
				Map.of("scope", RestrictionScope.GLOBAL))
				.list();
	}
}
