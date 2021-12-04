package org.ehealth.restrictions.resources.converters;

import javax.ws.rs.ext.ParamConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@link LocalDate} parser for endpoint parameters
 */
public class LocalDateConverter implements ParamConverter<LocalDate> {
    @Override
    public LocalDate fromString(String s) {
        return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
