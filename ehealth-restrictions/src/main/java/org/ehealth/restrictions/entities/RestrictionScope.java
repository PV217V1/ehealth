package org.ehealth.restrictions.entities;

import java.util.EnumSet;
import java.util.Set;

public enum RestrictionScope {
	UNDEFINED(),
	VACCINATED(1L),
	POST_CONTRACTION(2L),
	TESTED(3L),
	NOT_VACCINATED(4L);

	public final long flag;

	RestrictionScope() {
		flag = 0;
	}

	RestrictionScope(long val) {
		flag = 1L << val;
	}

	public EnumSet<RestrictionScope> asSet(long flags) {
		EnumSet<RestrictionScope> statusFlags = EnumSet.noneOf(RestrictionScope.class);
		statusFlags.forEach(statusFlag -> {
			long flagValue = statusFlag.flag;
			if ((flagValue & flags) == flagValue) {
				statusFlags.add(statusFlag);
			}
		});
		return statusFlags;
	}


	public long asFlags(Set<RestrictionScope> set) {
		return set.stream().mapToLong(statusFlag -> statusFlag.flag).reduce(0, (a, b) -> a | b);
	}
}
