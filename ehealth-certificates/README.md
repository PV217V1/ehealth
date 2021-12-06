## Vaccination Certificate Service

### Implemented by
- Karolína Kolouchová

### Overview
This microservice is responsible for CRUD operations on certificates. And can also return certificate for a given person along with info about the person.

- HTTP is exposed on port 8100
- API documentation available on endpoint `/openapi-ui`
- IDEA configuration for running the service is included

### Endpoints
- GET /certificates
- POST /certificates/create
- GET /certificates/forPerson/{id}
- GET /certificates/{id}
- DELETE /certificates/{id}/delete
- PUT /certificates/{id}/update

### Endpoint Dependencies
- ehealth-patients
