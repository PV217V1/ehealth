package soa.ehealth.certificates.enums;

public enum EVaccinationType {
    COMIRNATY("Comirnaty", "Pfizer/BioNTech", 2),
    JANSSEN("Janssen","Janssen Pharmaceutica", 1),
    VAXZEVIRA("Vaxzevira", "AstraZeneca", 2),
    SPIKEVAX("Spikevax", "Moderna", 2);

    private String name;

    private String manufacturer;

    private Integer numberOfDoses;

    EVaccinationType(String name, String manufacturer, Integer numberOfDoses) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.numberOfDoses = numberOfDoses;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Integer getNumberOfDoses() {
        return numberOfDoses;
    }
}
