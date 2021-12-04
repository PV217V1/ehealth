package soa.ehealth.medtests.endpoints;

import soa.ehealth.medtests.entities.TestType;

import java.time.LocalDate;

/**
 * DTO responsible for MedTest creation
 */
public class CreateMedTestDTO {

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

	public CreateMedTestDTO() { }

	public CreateMedTestDTO(Long personId, LocalDate testedAt, long validThroughSeconds, TestType testType) {
		this.personId = personId;
		this.testedAt = testedAt;
		this.validThroughSeconds = validThroughSeconds;
		this.testType = testType;
	}
}
