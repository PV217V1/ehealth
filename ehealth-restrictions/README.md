## Restrictions Service

### Implemented by
- Michal Hazdra

### Purpose
This microservice is responsible for CRUD operations for restrictions as they are introduced and revoked by the government.
This service depends on `ehealth-people` and `ehealth-certificates` and will display different results based
on whether the current user is vaccinated/contracted Covid-19/un-vaccinated.

### Endpoints

- HTTP is exposed on port `8110`
- Prometheus is using the default `9090`
- Grafana is also on the default `3000`

### Endpoint Dependencies

- ehealth-people
- ehealth-certificates
- ehealth-medtests

### Running the microservice

> I included my run configurations for IntelliJ IDEA for convenience.
That is the preferred way of starting the dev environment.

#### OR

1. The following environment variables must be defined either through\
   the system or a `.env` file in the same directory as `docker-compose.yml`

>> Docker and the Microservice
> 
> RESTRICTIONS_POSTGRES_PORT=PORT\
> PATIENTS_ENDPOINT_PORT=PORT\
> CERTIFICATES_ENDPOINT_PORT=PORT\
> MED_TESTS_ENDPOINT_PORT=PORT\
> SHARED_POSTGRES_PORT=PORT\
> RESTRICTIONS_ENDPOINT_PORT=PORT


2. Start the docker containers using the provided `docker-compose.yml`
3. ???
4. Go to `localhost:${RESTRICTIONS_ENDPOINT_PORT}/openapi-ui`
5. Enjoy the UI that we *did not* create.
