## Restrictions Service

### Implemented by
- Michal Hazdra

### Purpose
This microservice is responsible for CRUD operations for restrictions as they are introduced and revoked by the government.
This service depends on `ehealth-patients` and `ehealth-certificates` (TODO Name) and will display different results based
on whether the current user is vaccinated/contracted Covid-19/un-vaccinated.

> TODO