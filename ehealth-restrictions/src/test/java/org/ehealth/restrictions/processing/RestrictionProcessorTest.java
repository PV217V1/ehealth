package org.ehealth.restrictions.processing;

import org.ehealth.restrictions.entities.Restriction;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;


public class RestrictionProcessorTest {
	@Inject
	RestrictionProcessor processor;

	@BeforeEach
	void setup() {
		Restriction.deleteAll();
	}
}
