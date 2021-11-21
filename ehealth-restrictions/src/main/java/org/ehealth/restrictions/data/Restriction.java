package org.ehealth.restrictions.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity(name = "e_restriction")
public class Restriction extends PanacheEntity {
	public String title;
}