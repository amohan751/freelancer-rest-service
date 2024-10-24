# freelancer-rest-service
## Deliverables ##
  Your challenge is to create a Spring Boot REST API with the specified API calls below. You are free to use any frameworks/libraries you want and add additional features as long as the specified functionality is in place.
## Submission ##
  - Please ensure that you share the exact steps to run the application or 
  - include docker-compose and Dockerfile to run it seamlessly.
  - Build a Docker container for each service you implement.
  - The submission should be in the form of a GitHub repository link.
## Planning ##
### Resource: Freelancer ###
  - POST
    - Payload:
      - id
      - first name
      - last name
      - date of birth
      - gender
      - status
    - Responses
      - 200 ok
      - 201 ok
      - 404
      - 500
  - PUT
    - Payload:
      - id
      - verified
    - Responses
      - 200 ok
      - 201 ok
      - 404
      - 500
  - DELETE
    - Payload:
      - id
      - mark for delete
    - Responses
      - 200 ok
      - 201 ok
      - 404
      - 500
  - GET
    - Query parameter
      - all
      - pageLimit
    - Responses
      - 200 payload
        - id
        - first name
        - last name
        - date of birth
        - gender
      - 404
        - not found message
      - 500
        - server error message
  - GET
    - Query parameter
      - newFreelancers
      - pageLimit
    - Responses
      - 200 payload
        - id
        - first name
        - last name
        - date of birth
        - gender
      - 404
        - not found message
      - 500
        - server error message
  - GET
    - Query parameter
      - allDeletedFreelancers
      - pageLimit
    - Responses
      - 200 payload
        - id
        - first name
        - last name
        - date of birth
        - gender
      - 404
        - not found message
      - 500
        - server error message
## Setting up application ##
  The application is built using spring-boot 3.3.4 and Java 17. The Docker file committed with this application has the necessary details to compile and run the application.
  use "docker-compose build" and "docker-compose up" to build and run the application.
## API Specification ##
  API specification is added with the swagger document named "Freelancer-rest-service_APIspecification-1.0"
