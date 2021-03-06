package org.ehealth.restrictions.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Entity for representing a government issued restriction
 */
@Entity(name = "e_restriction")
public class Restriction extends PanacheEntity {

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 1024)
	private String description;

	@Column(nullable = false)
	private LocalDate issued;

	@Column(nullable = false)
	private LocalDate validSince;

	private LocalDate expired;

	@Column(nullable = false)
	private RestrictionScope scope;

	public Restriction() { }

	public Restriction(String title, String description, LocalDate issued, LocalDate validSince, LocalDate expired, RestrictionScope scope) {
		this.title = title;
		this.description = description;
		this.issued = issued;
		this.validSince = validSince;
		this.expired = expired;
		this.scope = scope;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RestrictionScope getScope() {
		return scope;
	}

	public void setScope(RestrictionScope scope) {
		this.scope = scope;
	}

	public LocalDate getIssued() {
		return issued;
	}

	public void setIssued(LocalDate issued) {
		this.issued = issued;
	}

	public LocalDate getValidSince() {
		return validSince;
	}

	public void setValidSince(LocalDate validSince) {
		this.validSince = validSince;
	}

	public LocalDate getExpired() {
		return expired;
	}

	public void setExpired(LocalDate expired) {
		this.expired = expired;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Restriction)) return false;

		Restriction that = (Restriction) o;

		if (!getTitle().equals(that.getTitle())) return false;
		if (!getDescription().equals(that.getDescription())) return false;
		if (!getIssued().equals(that.getIssued())) return false;
		if (!getValidSince().equals(that.getValidSince())) return false;
		if (getExpired() != null ? !getExpired().equals(that.getExpired()) : that.getExpired() != null) return false;
		return getScope() == that.getScope();
	}

	@Override
	public int hashCode() {
		int result = getTitle().hashCode();
		result = 31 * result + getDescription().hashCode();
		result = 31 * result + getIssued().hashCode();
		result = 31 * result + getValidSince().hashCode();
		result = 31 * result + (getExpired() != null ? getExpired().hashCode() : 0);
		result = 31 * result + getScope().hashCode();
		return result;
	}
}