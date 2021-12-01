package org.ehealth.restrictions.resiliency;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.ehealth.restrictions.resiliency.base.BaseHealthCheck;

/**
 * Class responsible for monitoring the liveness of the patients-microservice
 */
@Liveness
public class PatientsLivenessCheck extends BaseHealthCheck {

    @ConfigProperty(name = "patients-service-endpoint")
    String url;

    @Override
    public HealthCheckResponse call() {
        return getHealthCheckResponse(url, "Patients Status");
    }
}