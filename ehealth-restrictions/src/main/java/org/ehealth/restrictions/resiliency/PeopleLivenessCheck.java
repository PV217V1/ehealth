package org.ehealth.restrictions.resiliency;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.ehealth.restrictions.resiliency.base.BaseHealthCheck;

/**
 * Class responsible for monitoring the liveness of the people-microservice
 */
@Liveness
public class PeopleLivenessCheck extends BaseHealthCheck {

    @ConfigProperty(name = "people-service-endpoint/mp-rest/url")
    String url;

    @Override
    public HealthCheckResponse call() {
        return getHealthCheckResponse(url, "People Status");
    }
}