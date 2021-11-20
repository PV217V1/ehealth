# E-health Project

E-Health is a small API Microservice project for people to register into and get information about Covid-19. This project was created as a group assignment in the course PV217 Software Oriented Architecture.

Team members:

- Mr.Yanno Ban *(528066)*
- Ms.Karolina Kokuchova *(456120)*
- Michal Hazdra *(469081)*

## Patient Service

- NewRegistration()
- UpdatePatient(...)
- GetPatientInfo(..)

## Vaccination Certificate Service

- NewCertificate()
- UpdateCertificate()
- GetPatientCertificate()

# Technical Requirement

We are using the Quarkus framwork developed by Red Hat. https://quarkus.io

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