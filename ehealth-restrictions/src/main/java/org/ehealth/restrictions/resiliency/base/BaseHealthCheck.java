package org.ehealth.restrictions.resiliency.base;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 * Base class responsible for building the health check response for a generic URL
 */
public abstract class BaseHealthCheck implements HealthCheck {
    
    protected HealthCheckResponse getHealthCheckResponse(String url, String header) {
        HealthCheckResponseBuilder builder = HealthCheckResponse.builder()
                .name(header);

        Client client = null;

        try {
            client = ClientBuilder.newClient();
            Response response = client.target(url).path("/q/health")
                    .request().get();
            response.close();
            builder.up();
        } catch (Exception e) {
            builder.down()
                    .withData("Exception", e.getMessage());
        } finally {
            if (client != null) {
                client.close();
            }
        }

        return builder.build();
    }
}
