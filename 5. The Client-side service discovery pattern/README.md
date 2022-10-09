~ AN EXAMPLE OF APPLYING THE "Client-side service discovery" PATTERN ~

* The library / project used to apply the pattern: Netflix Eureka.

* I have created a Service1 service which will call an API of Service2
  service, created two instances (two copies) of Service2 service are
  a Service2A service and a Service2B service which has the same
  functions and list of APIs like Service2A service will have the same
  name are "Service2", and created a service registry storing locations
  of those three services.

* After running the Eureka server, you can check which services have
  registered with the Eureka server by navigating to http://localhost:8761
  and look at the "Instances currently registered with Eureka" part. At the
  beginning, there are no instances register with the Eureka server.

* The Service2A and the Service2B service both using the "Service2" name, and 
  they will take turn handling any requests sending to service named Service2. 
  - The Service2A service will respond to any requests sent to 
    http://localhost:8082/home or http://Service2/home the "This is the 
    Service2A home page." string. 
  - The Service2B service will respond to any requests sent to
    http://localhost:8083/home or http://Service2/home the "This is the 
    Service2B home page." string. 

* After running the Service2A and the Service2B service, if you check the
  information of the Eureka server by navigating to http://localhost:8761, 
  you can see that there are two services named Service2 that registered 
  their address to the Eureka server.
  => Other services can go to the Eureka server to get the location of 
  Service2 services.

* If you send a request to http://localhost:8081/home (the Service1 service),
  the Service1 service will send a request to http://Service2/home, you will
  use the name of the destination service as "Service2" instead of the address 
  of the service as "localhost:8082" or "localhost:8082".

* After running the Service1 service, if you navigate to http://localhost:8761, 
  you can see that the Service1 service has registered to the Eureka server.

* After running those services, if you navigate to http://localhost:8081/home
  and refresh the page many times, you will see two strings "This is the
  Service2A home page." and "This is the Service2B home page." are displayed
  alternately. 
  - If you navigate to http://localhost:8081/home, and you get no response, 
    you can wait a few seconds and then try again.
  - If you turn off one instance of the Service2 service, you will receive only
    the response of the remaining service. If after you turn off one instance of
    the Service2 service and get no response, then it is because the Service1
    service still use the address of the dead service in its cache, you just need
    to wait a few seconds for the Service1 service to reset its cache.

Congratulations! You have just created a Eureka server and make services sending
requests to each other through the service logical name. This will abstract away the
physical address and make it easier for services to scale horizontally by add more
service instances having that service name.