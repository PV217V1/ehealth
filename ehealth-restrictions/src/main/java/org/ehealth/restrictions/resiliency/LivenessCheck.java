package org.ehealth.restrictions.resiliency;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/**
 * Class responsible for monitoring the liveness of the microservice
 */
@Liveness
public class LivenessCheck implements HealthCheck {
	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.up("alive");
	}
}