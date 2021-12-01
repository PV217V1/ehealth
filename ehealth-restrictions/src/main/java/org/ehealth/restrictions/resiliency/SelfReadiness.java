package org.ehealth.restrictions.resiliency;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.ehealth.restrictions.entities.Restriction;

import java.util.Random;

/**
 * Class responsible for monitoring the readiness of the database and the microservice
 */
@Readiness
public class SelfReadiness implements HealthCheck {

    private static final int CALLS_DOWN = 10;
    private int callsLeft = 0;

    @Override
    public HealthCheckResponse call() {
        try {
            Restriction.count();
        } catch (Exception e) {
            return HealthCheckResponse.down("database down");
        }

        // Simulated DB downtime
        if (callsLeft > 0) {
            return HealthCheckResponse.down("dead - callsLeft: " + callsLeft--);
        }
        if (new Random().nextInt(100) > 95) {
            callsLeft = CALLS_DOWN;
            return HealthCheckResponse.down("dead");
        }

        if (new PatientsLivenessCheck().call().getStatus() == HealthCheckResponse.Status.DOWN) {
            return HealthCheckResponse.builder().up().withData("ISSUE", "Patients DOWN").build();
        }

        if (new CertificatesLivenessCheck().call().getStatus() == HealthCheckResponse.Status.DOWN) {
            return HealthCheckResponse.builder().up().withData("ISSUE", "Certificates DOWN").build();
        }

        if (new MedTestsLivenessCheck().call().getStatus() == HealthCheckResponse.Status.DOWN) {
            return HealthCheckResponse.builder().up().withData("ISSUE", "Medtests DOWN").build();
        }


        return HealthCheckResponse.up("alive");
    }
}
