package org.ehealth.restrictions.processing;

import org.ehealth.restrictions.entities.RestrictionScope;
import org.jetbrains.annotations.NotNull;
import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.endpoints.dto.PersonMedRecord;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RestrictionProcessorImpl implements RestrictionProcessor {

	@Override
	@NotNull
	public List<Restriction> process(PersonMedRecord record) {
		// When vaccinated and with a valid test only the global restrictions apply
		if (record.certificate != null && !record.tests.isEmpty()) {
			return getGlobalRestrictions();
		}

		// When not vaccinated but with a valid test global and non-vaccinated restrictions apply
		if (record.certificate == null && !record.tests.isEmpty()) {
			return Restriction.find(scopeQuery(RestrictionScope.GLOBAL, RestrictionScope.NOT_VACCINATED))
					.list();
		}

		// When vaccinated without a valid test global and not tested restrictions apply
		if (record.certificate != null) {
			return Restriction.find(scopeQuery(RestrictionScope.GLOBAL, RestrictionScope.NOT_TESTED))
					.list();
		}

		// When none of the cases apply you have to obey all the restrictions
		return Restriction.find(scopeQuery(RestrictionScope.values()))
				.list();
	}

	@Override
	public List<Restriction> getGlobalRestrictions() {
		return Restriction.find(scopeQuery(RestrictionScope.GLOBAL))
				.list();
	}

	@Override
	public List<Restriction> getRestrictionsForDate(LocalDate date) {
		String query = "validSince <= :date AND ((expired IS NOT NULL AND expired > :date) OR expired IS NULL)";
		return Restriction.find(query, Map.of("date", date)).list();
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
