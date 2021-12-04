package soa.ehealth.medtests.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Entity for representing a medical test performed by a person
 */
@Entity(name = "e_medtests")
@Schema
public class MedTest extends PanacheEntity {

	@NotNull
	private Long personId;

	@NotNull
	private LocalDate testedAt;

	private long validThroughSeconds;

	@NotNull
	private TestType testType;

	public MedTest() { }

	public MedTest(@NotNull Long personId, @NotNull LocalDate testedAt, long validThroughSeconds, @NotNull TestType testType) {
		this.personId = personId;
		this.testedAt = testedAt;
		this.validThroughSeconds = validThroughSeconds;
		this.testType = testType;
	}

	public @NotNull Long getPersonId() {
		return personId;
	}

	public void setPersonId(@NotNull Long personId) {
		this.personId = personId;
	}

	public @NotNull LocalDate getTestedAt() {
		return testedAt;
	}

	public void setTestedAt(@NotNull LocalDate testedAt) {
		this.testedAt = testedAt;
	}

	public long getValidThroughSeconds() {
		return validThroughSeconds;
	}

	public void setValidThroughSeconds(long validThroughSeconds) {
		this.validThroughSeconds = validThroughSeconds;
	}

	public @NotNull TestType getTestType() {
		return testType;
	}

	public void setTestType(@NotNull TestType testType) {
		this.testType = testType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MedTest)) return false;

		MedTest medTest = (MedTest) o;

		if (validThroughSeconds != medTest.validThroughSeconds) return false;
		if (!getPersonId().equals(medTest.getPersonId())) return false;
		if (!getTestedAt().equals(medTest.getTestedAt())) return false;
		return getTestType() == medTest.getTestType();
	}

	@Override
	public int hashCode() {
		int result = getPersonId().hashCode();
		result = 31 * result + getTestedAt().hashCode();
		result = 31 * result + (int) (validThroughSeconds ^ (validThroughSeconds >>> 32));
		result = 31 * result + getTestType().hashCode();
		return result;
	}
}
