## Medical Tests Service

### Implemented by
- Michal Hazdra

### Purpose
This microservice is responsible for CRUD operations for medical tests.

### Endpoints

- HTTP is exposed on port `8090`
- Prometheus is using the default `9090`
- Grafana is also on the default `3000`

### Endpoint Dependencies

- ehealth-people

### Running the microservice

> I included my run configurations for IntelliJ IDEA for convenience.
That is the preferred way of starting the dev environment. 

#### OR

1. The following environment variables must be defined either through\
   the system or a `.env` file in the same directory as `docker-compose.yml`

>> Docker and the Microservice
>
> PATIENTS_ENDPOINT_PORT=PORT\
> MED_TESTS_ENDPOINT_PORT=PORT\
> SHARED_POSTGRES_PORT=PORT


2. Start the docker containers using the provided `docker-compose.yml`
3. ???
4. Go to `localhost:${MED_TESTS_ENDPOINT_PORT}/openapi-ui`
5. Enjoy the UI that we *did not* create.
