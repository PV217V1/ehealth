package org.ehealth.restrictions.entities;

/**
 * The scope for who the restriction applies
 * TODO this system is not extensible...
 */
public enum RestrictionScope {
	/**
	 * The restriction is applied globally to everyone
	 */
	GLOBAL,
	/**
	 * This restriction is applied to all who where vaccinated
	 */
	VACCINATED,
	/**
	 * This restriction is applied to all who contracted a disease
	 * and are within a time where the natural immunity is assumed
	 */
	POST_CONTRACTION,
	/**
	 * This restriction is applied to all who undergone PCR/Antigen or any other valid test
	 */
	TESTED,
	/**
	 * This restriction is applied to all who are not tested nor vaccinated
	 */
	NOT_VACCINATED
}
