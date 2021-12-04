package soa.ehealth.medtests;

import soa.ehealth.medtests.entities.MedTest;
import soa.ehealth.medtests.entities.TestType;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class DataHelper {
	public static List<MedTest> getMedTests() {
		return List.of(
				new MedTest(1L,
						LocalDate.of(2021, Month.APRIL, 20),
						604800, TestType.PCR),
				new MedTest(2L,
						LocalDate.of(2021, Month.APRIL, 20),
						604800, TestType.PCR),
				new MedTest(2L,
						LocalDate.of(2021, Month.JUNE, 10),
						172800, TestType.ANTIGEN),
				new MedTest(3L,
						LocalDate.of(2021, Month.JUNE, 10),
						2592000, TestType.OTHER),
				new MedTest(2L,
						LocalDate.of(2021, Month.JULY, 20),
						172800, TestType.ANTIGEN),
				new MedTest(1L,
						LocalDate.of(2021, Month.AUGUST, 11),
						604800, TestType.PCR),
				new MedTest(1L,
						LocalDate.of(2021, Month.AUGUST, 11),
						172800, TestType.ANTIGEN),
				new MedTest(2L,
						LocalDate.of(2021, Month.AUGUST, 12),
						604800, TestType.PCR),
				new MedTest(3L,
						LocalDate.of(2021, Month.SEPTEMBER, 2),
						604800, TestType.PCR),
				new MedTest(2L,
						LocalDate.of(2021, Month.OCTOBER, 15),
						604800, TestType.PCR),
				new MedTest(1L,
						LocalDate.of(2021, Month.OCTOBER, 12),
						172800, TestType.ANTIGEN),
				new MedTest(2L,
						LocalDate.of(2021, Month.NOVEMBER, 20),
						604800, TestType.PCR),
				new MedTest(1L,
						LocalDate.of(2021, Month.NOVEMBER, 20),
						604800, TestType.PCR),
				new MedTest(3L,
						LocalDate.of(2021, Month.NOVEMBER, 20),
						172800, TestType.ANTIGEN),
				new MedTest(2L,
						LocalDate.of(2021, Month.NOVEMBER, 20),
						2592000, TestType.OTHER)
		);
	}
}
