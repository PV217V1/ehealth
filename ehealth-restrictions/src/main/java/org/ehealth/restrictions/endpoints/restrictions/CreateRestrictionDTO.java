package org.ehealth.restrictions.endpoints.restrictions;

import org.ehealth.restrictions.entities.RestrictionScope;

import java.time.LocalDate;

/**
 * DTO responsible for Restriction creation
 */
public class CreateRestrictionDTO {
	/**
	 * The title/heading of the restriction
	 */
	public String title;

	/**
	 * The detailed description
	 */
	public String description;

	/**
	 * The date the restriction was introduced
	 */
	public LocalDate issued;

	/**
	 * The date the restriction comes in effect
	 */
	public LocalDate validSince;

	/**
	 * The date the restriction ceases to be effective, can be {@link null} if restriction is still in effect
	 */
	public LocalDate expired;

	/**
	 * Who the restriction applies to
	 */
	public RestrictionScope scope;

	public CreateRestrictionDTO() { }

	public CreateRestrictionDTO(String title, String description, LocalDate issued, LocalDate validSince, LocalDate expired, RestrictionScope scope) {
		this.title = title;
		this.description = description;
		this.issued = issued;
		this.validSince = validSince;
		this.expired = expired;
		this.scope = scope;
	}
}
