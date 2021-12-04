package org.ehealth.restrictions.endpoints.dto.medtests;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

/**
 * Medical test DTO as defined by the <strong>ehealth-medtests</strong> endpoint
 */
public class MedTestDTO {

	/**
	 * The database assigned ID
	 */
	public long id;

	/**
	 * The ID of the person this test belongs to
	 */
	public long personId;

	/**
	 * The date at which the person underwent the testing
	 */
	public LocalDate testedAt;

	/**
	 * The validity duration expressed as UNIX timestamp
	 */
	public long validThroughSeconds;

	/**
	 * The type of the test performed
	 */
	public TestType testType;

	public MedTestDTO() { }

	public MedTestDTO(long id, @NotNull Long personId, @NotNull LocalDate testedAt, long validThroughSeconds, @NotNull TestType testType) {
		this.id = id;
		this.personId = personId;
		this.testedAt = testedAt;
		this.validThroughSeconds = validThroughSeconds;
		this.testType = testType;
	}
}
