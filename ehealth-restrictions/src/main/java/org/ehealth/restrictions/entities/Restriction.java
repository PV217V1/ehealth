package org.ehealth.restrictions.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "e_restriction")
public class Restriction extends PanacheEntity {

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, length = 1024)
	private String description;

	@Column(nullable = false)
	private LocalDate issued;

	private LocalDate expired;

	@Column(nullable = false)
	private RestrictionScope scope;

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

	public LocalDate getExpired() {
		return expired;
	}

	public void setExpired(LocalDate expired) {
		this.expired = expired;
	}
}