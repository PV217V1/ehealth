package org.ehealth.restrictions.processing;

import org.ehealth.restrictions.entities.RestrictionScope;
import org.jetbrains.annotations.NotNull;
import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.endpoints.dto.PatientMedRecord;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RestrictionProcessorImpl implements RestrictionProcessor {

	@Override
	@NotNull
	public List<Restriction> process(PatientMedRecord record) {
		if (!record.certificates.isEmpty()) {
			return Restriction.find(scopeQuery(RestrictionScope.GLOBAL, RestrictionScope.VACCINATED))
					.list();
		}

		return Restriction.find(scopeQuery(RestrictionScope.GLOBAL, RestrictionScope.NOT_VACCINATED))
				.list();
	}

	@Override
	public List<Restriction> getGlobalRestrictions() {
		return Restriction.find(scopeQuery(RestrictionScope.GLOBAL))
				.list();
	}

	private String scopeQuery(RestrictionScope... scopes) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < scopes.length; i++) {
			builder.append("scope = ").append(RestrictionScope.class.getName()).append('.').append(scopes[i]);
			if (i < scopes.length - 1) {
				builder.append(" or ");
			}
		}

		return builder.toString();
	}
}
