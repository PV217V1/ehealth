package org.ehealth.restriction.data

import io.quarkus.hibernate.orm.panache.PanacheEntity
import javax.persistence.Entity

@Entity
class Restriction : PanacheEntity() {
	lateinit var title: String
}