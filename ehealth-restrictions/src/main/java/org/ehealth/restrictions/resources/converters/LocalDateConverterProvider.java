package org.ehealth.restrictions.resources.converters;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

@Provider
public class LocalDateConverterProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> forClass, Type type, Annotation[] annotations) {
		if (forClass.equals(LocalDate.class)) {
			return (ParamConverter<T>) new LocalDateConverter();
		}
		return null;
	}
}
