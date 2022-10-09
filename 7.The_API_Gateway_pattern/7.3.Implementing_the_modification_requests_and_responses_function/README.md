~ AN EXAMPLE OF THE MODIFICATION REQUESTS AND RESPONSES FUNCTION ~

* I will replace the old API endpoint is “/ser1/home” with 
  the new API endpoint is “/ser1/homeV2” of the Server1Service 
  service and implement the filter component in the 
  GatewayService service to rewrite any request path 
  “/ser1/home” into new path “/ser1/homeV2” before forwarding 
  the request to the Server1Service service without modifying 
  the ClientService service source code.

* I will modify the 10th and 12th line in the Server1Service 
  service controller file (modify the url and the response) as 
  below:
  - value = "/ser1/home" => value = "/ser1/homeV2"
  - return "Server 1 home" => return "Server 1 home new version"
  => The Server1Service service will accept any requests sent to 
     “http://localhost:8081/ser1/homeV2” and respond the “Server 
     1 home new version” string. It will not accept any requests 
     sent to “http://localhost:8081/ser1/home” because I have 
     deleted the “/ser1/home” API endpoint.

* I will not change the source code of the ClientService service. 
  At the 14th line in the controller file, the ClientService 
  service still sends request to the Server1Service service with 
  the old path “/ser1/home” but the Server1Service service just 
  exposes only the “/ser1/homeV2” API endpoint not the “/ser1/home” 
  endpoint.

* I will use the RewritePath filter in the GatewayService service 
  to modify the path of requests sent to the Server1Service service 
  with path “/ser1/home” to the new path “/ser1/homeV2”. I have  
  added the 13th and 14th line in the application.yml file of the 
  GatewayService service.

* If you turn on all four services are the ClientService service,
  the Server1Service service, the Server2Service service, and the
  GatewayService service:
  - If you navigate to http://localhost:8083/1, you will see the
    "Server 1 home new version" string in your browser.
  - If you navigate to http://localhost:8083/2, you will see the
    "Server 2 home" string in your browser.
  => Implement the filter component of the API gateway successfully.

Congratulation, you have implemented successfully the filter function 
on the API gateway and use the API gateway to modify HTTP requests and 
HTTP responses to achieve a certain goal more easily.