package org.ehealth.restrictions.resiliency;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.ehealth.restrictions.entities.Restriction;

import java.util.Random;

/**
 * Class responsible for monitoring the readiness of the database of the microservice
 */
@Readiness
public class ReadinessCheck implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		try {
			Restriction.count();
		} catch (Exception e) {
			return HealthCheckResponse.down("database down");
		}
		if (new Random().nextInt(100) > 95) {
			return HealthCheckResponse.down("dead");
		}
		return HealthCheckResponse.up("alive");
	}
}
