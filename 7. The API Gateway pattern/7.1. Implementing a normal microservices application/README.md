~ AN EXAMPLE OF A APPLICATION DOESN'T USE THE API GATEWAY ~

* Now I will implement a small microservice application doesn't 
  use the API Gateway pattern. I will develop this application 
  with the API Gateway pattern later, so you can see benefits 
  that the API gateway brings us. I will create a ClientService 
  service that sends requests to other two services, a 
  Server1Service service and a Server2Service service. 

* After running the Server1Service and the Server2Service service:
  - If you navigate to http://localhost:8081/ser1/home, you will 
    see the "Server 1 home" string in your browser.
  - If you navigate to http://localhost:8082/ser2/home, you will
    see the "Server 2 home" string in your browser.

* After running the ClientService service:
  - If you navigate to http://localhost:8083/1, the ClientService
    service will send a request to http://localhost:8081/ with the
    path is "/ser1/home". You will see the "Server 1 home" string 
    which is responded by the Server1Service service in your browser.
  - If you navigate to http://localhost:8083/2, the ClientService
    service will send a request to http://localhost:8082/ with the
    path is "/ser2/home". You will see the "Server 2 home" string
    which is responded by the Server2Service service in your browser.
  => If the ClientService service want to send requests to many 
     services, it has to remember many locations such as 
     http://localhost:8081/ is the location of the Server1Service
     service, http://localhost:8082/ is the location of the 
     Server2Service service,... (or it has to remember many logical
     names if it uses the Eureka server).