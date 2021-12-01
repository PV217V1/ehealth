package org.ehealth.restrictions.common;

import org.ehealth.restrictions.endpoints.dto.PatientMedRecord;
import org.ehealth.restrictions.endpoints.dto.patients.PatientDTO;
import org.ehealth.restrictions.entities.Restriction;
import org.ehealth.restrictions.entities.RestrictionScope;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class DataHelper {
    public static List<Restriction> getRestrictions() {
        return List.of(
                new Restriction("1", "Description 1",
                        LocalDate.of(2021, Month.APRIL, 20),
                        LocalDate.of(2021, Month.APRIL, 22),
                        LocalDate.of(2021, Month.MAY, 20), RestrictionScope.GLOBAL),
                new Restriction("2", "Description 2",
                        LocalDate.of(2021, Month.APRIL, 20),
                        LocalDate.of(2021, Month.APRIL, 24),
                        LocalDate.of(2021, Month.MAY, 20), RestrictionScope.GLOBAL),
                new Restriction("3", "Description 3",
                        LocalDate.of(2021, Month.JUNE, 10),
                        LocalDate.of(2021, Month.JUNE, 12),
                        LocalDate.of(2021, Month.JULY, 20), RestrictionScope.GLOBAL),
                new Restriction("4", "Description 4",
                        LocalDate.of(2021, Month.JUNE, 10),
                        LocalDate.of(2021, Month.JUNE, 12),
                        LocalDate.of(2021, Month.JULY, 20), RestrictionScope.NOT_VACCINATED),
                new Restriction("5", "Description 5",
                        LocalDate.of(2021, Month.JULY, 20),
                        LocalDate.of(2021, Month.JULY, 22),
                        null, RestrictionScope.NOT_VACCINATED),
                new Restriction("6", "Description 6",
                        LocalDate.of(2021, Month.AUGUST, 11),
                        LocalDate.of(2021, Month.AUGUST, 12),
                        null, RestrictionScope.NOT_VACCINATED),
                new Restriction("7", "Description 7",
                        LocalDate.of(2021, Month.AUGUST, 11),
                        LocalDate.of(2021, Month.AUGUST, 12),
                        LocalDate.of(2021, Month.NOVEMBER, 20), RestrictionScope.POST_CONTRACTION),
                new Restriction("8", "Description 8",
                        LocalDate.of(2021, Month.AUGUST, 11),
                        LocalDate.of(2021, Month.AUGUST, 12),
                        null, RestrictionScope.POST_CONTRACTION),
                new Restriction("9", "Description 9",
                        LocalDate.of(2021, Month.SEPTEMBER, 1),
                        LocalDate.of(2021, Month.SEPTEMBER, 2),
                        null, RestrictionScope.POST_CONTRACTION),
                new Restriction("10", "Description 10",
                        LocalDate.of(2021, Month.OCTOBER, 12),
                        LocalDate.of(2021, Month.OCTOBER, 15),
                        null, RestrictionScope.VACCINATED),
                new Restriction("11", "Description 11",
                        LocalDate.of(2021, Month.OCTOBER, 12),
                        LocalDate.of(2021, Month.OCTOBER, 15),
                        LocalDate.of(2021, Month.OCTOBER, 16), RestrictionScope.VACCINATED),
                new Restriction("12", "Description 12",
                        LocalDate.of(2021, Month.NOVEMBER, 18),
                        LocalDate.of(2021, Month.NOVEMBER, 20),
                        LocalDate.of(2021, Month.DECEMBER, 1), RestrictionScope.VACCINATED),
                new Restriction("13", "Description 13",
                        LocalDate.of(2021, Month.NOVEMBER, 18),
                        LocalDate.of(2021, Month.NOVEMBER, 20),
                        null, RestrictionScope.TESTED),
                new Restriction("14", "Description 14",
                        LocalDate.of(2021, Month.NOVEMBER, 18),
                        LocalDate.of(2021, Month.NOVEMBER, 20),
                        null, RestrictionScope.TESTED),
                new Restriction("15", "Description 15",
                        LocalDate.of(2021, Month.NOVEMBER, 18),
                        LocalDate.of(2021, Month.NOVEMBER, 20),
                        null, RestrictionScope.TESTED)
        );
    }

    public static PatientMedRecord getPatientRecord() {
        PatientDTO patient = new PatientDTO();
        patient.name = "John";
        patient.age = "28";
        patient.address = "Some Address";
        patient.telephone = "012345678";
        return new PatientMedRecord(patient, List.of(), List.of());
    }
}
