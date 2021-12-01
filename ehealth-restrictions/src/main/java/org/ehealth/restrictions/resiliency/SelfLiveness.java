package org.ehealth.restrictions.resiliency;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.ehealth.restrictions.resiliency.base.BaseHealthCheck;

/**
 * Class responsible for monitoring the liveness the microservice
 */
@Liveness
public class SelfLiveness extends BaseHealthCheck {

    @Override
    public HealthCheckResponse call() {
        if (new SelfReadiness().call().getStatus() == HealthCheckResponse.Status.UP) {
            return HealthCheckResponse.up("If you are seeing this the I am working fine");
        } else {
            return HealthCheckResponse.up("The endpoint is up, but the database is having issues!");
        }
    }
}
