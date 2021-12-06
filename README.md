# E-health Project

E-Health is a small API Microservice project for people to register into and get information about Covid-19.
This project was created as a group assignment in the course PV217 Software Oriented Architecture.

### Team members:

- Mr.Yanno Ban *(528066)*
- Karolina Kolouchova *(456120)*
- Michal Hazdra *(469081)* 

## Overview

Each microservice project has a separate README that contains project specific information.

Each project exposes the following endpoints:

### OpenAPI
> /openapi

provides OpenAPI specification document

> /openapi-ui

provides Swagger UI endpoint (also included in release configuration for simplicity)

## The I's IV's and V's

### i) description of the project
We started this project as a backed for a website that gathers and displays
all COVID-19 related data for citizens, There are so many restriction that may
easily get lost in what is currently restricted and what is allowed. Not all
restrictions apply to everyone that is what makes it confusing.

We wanted to be able to take a user's profile (vaccination and test record) and display
all restriction that are currently in effect.


### ii) a story/scenario of usage
The main user story is definitely:

*As a user I want to know what restrictions apply to me.*

We did not implement a working frontend for the services, and unfortunately neither User authentication.
If we had that, a user would simply log in to the website and all important information such as:
- the dates of the next vaccine dose
- validity periods of tests/vaccines
- restriction that apply to the person

would be presented in one place.
Similarly to [Vaccination portal of MZCR](https://ocko.uzis.cz/), but with the added benefit of active restrictions.


### iii) why you think a microservice architecture can be appropriate
Tests and COVID certificates and restrictions are three separate things that, when doing basic CRUD operations, do not care about each other.
For this reason a microservice architecture is great as the failure of one does not influence the others.


### iv) benefits of the using microservices in this project
Once we defined the contract everyone worked on their assigned project.
Everyone had the freedom to implement things the way they wanted.
The startup times for debugging were much better compared to monoliths, this is partly thanks to the Quarkus framework however.

### v) drawbacks of microservices in this case.
With every microservice having its own data. And with dependencies across said data.
Maintaining consistent state is difficult. So in the end we are not doing any validation...
Having a working and communicating team is important. We sometimes struggled with that. 


## Technical Requirements

+ For the implementation, try to keep with the technology stack presented during the lecture Quarkus, Docker/Podman, etc... All the following points will be covered during the block lectures:

+ Have the API design & contract available in the project documentation or integrated in the Quarkus project (e.g., using https://swagger.io - https://quarkus.io/guides/openapi-swaggerui)

+ Have some monitoring part for the services collecting metrics about usage (https://quarkus.io/guides/microprofile-metrics);

+ Have at least one service that can show self-healing capabilities (https://quarkus.io/guides/microprofile-health);

+ All services have some meaningful tests running (https://quarkus.io/guides/getting-started-testing);

+ Have some simple database support for at least one service (https://quarkus.io/guides/hibernate-orm-panache);

+ Process monitoring data and visualization with Prometheus + Grafana (https://quarkus.io/guides/microprofile-metrics);

+ (optional) Use Jaeger for tracing (https://quarkus.io/guides/opentracing);

+ (optional) Use of Kafka for asynchronous messaging (https://quarkus.io/guides/kafka)

+ (optional) Use of authentication for some microservices with JWT (JSON Web Tokens);

+ (optional) The project can be deployed with Docker/Podman;

+ (optional) The project can be deployed on Kubernetes/Minikube or Openshift/CRC;

+ (optional) show the usage of native executable with GraalVM, discussing advantages/disadvantages (e.g., what are the performance improvement running the scenario you created);

+ (optional) Not directly covered in this course: however, if you have previous experience or want to try, you can showcase polyglot microservices (implemented in different programming languages/frameworks);

+ On your GitHub page of the project. give a i) description of the project, ii) a story/scenario of usage, iii) why you think a microservice architecture can be appropriate, iv) benefits of the using microservices in this project, v) drawbacks of microservices in this case.

+ Have a scenario that you can run that showcase some reactive properties of the system (those that you implemented): Responsiveness (quick response / always on), resiliency (self-healing service), elasticity (how the system can scale up/down when needed), message-driveness (asynchronous messages with loosely coupled / location-transparent services);