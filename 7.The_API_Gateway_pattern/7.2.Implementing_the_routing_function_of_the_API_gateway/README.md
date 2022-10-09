~ AN EXAMPLE OF THE ROUTING FUNCTION OF THE API GATEWAY ~

* Instead of the client sending two requests directly to two 
  services, the client sends those two requests to only the 
  APIGatewayService service. The APIGatewayService service 
  will forward that request to the corresponding destination 
  service. After the APIGatewayService service receives the 
  response from the destination service, the APIGatewayService 
  service will respond back to the client.

* I will modify the 14th and 22th line in the ClientService 
  service controller file (modify the url) as below:
  - String url = "http://localhost:8081/ser1/home" =>
    String url = "http://localhost:8080/ser1/home"
  - String url = "http://localhost:8082/ser2/home" =>
    String url = "http://localhost:8080/ser2/home"
  => What is modified here is that the location of the 
     destination services of the requests sent by the 
     ClientService service is the address of the 
     GatewayService service (http://localhost:8080). The two 
     requests that should have been sent to the Server1Service 
     service (http://localhost:8081) and the Server2Service 
     service (http://localhost:8082) were both sent to the 
     GatewayService service (http://localhost:8080). 

* If you turn on all four services are the ClientService service, 
  the Server1Service service, the Server2Service service, and the 
  GatewayService service, navigate to http://localhost:8083/1 
  and http://localhost:8083/2, you will see the "Server 1 home" 
  and "Server 1 home" string in your browser => Implement the
  routing function of the API gateway successfully.

* After you navigate to http://localhost:8083/1 on your web browser,
  what happens behind the screen is:
  - The web browser sends a request to the ClientService service 
    because the request’s destination address is http://localhost:8083.
  - The ClientService service call the “home1()” method because the 
    request’s path is “/1”.
  - The ClientService service sends a request to the GatewayService 
    service because the request’s destination address is 
    http://localhost:8080. This request’s path is “/ser1/home”.
  - The GatewayService service receives the request from the ClientService 
    service. It checks the first route, the Server-1 id route, and the 
    request’s path (“/ser1/home”) matches the predicate’s path (“/ser1/**”) 
    so it forwards the request to the uri http://localhost:8081, 
    the Server1Service service. 
  - The Server1Service service receives the request from the GatewayService 
    service. It sees that the request’s path is “/ser1/home” so it calls 
    the “home()” method and responds the “Server 1 home” string back to the 
    GatewayService service.
  - The GatewayService service receives the response from the Server1Service 
    service. It responds that “Server 1 home” string back to the 
    ClientService service.
  - The ClientService service receives the response from the GatewayService 
    service and the browser displays the “Server 1 home” string on the screen.
  - The process is the same if you navigate to http://localhost:8083/2 on your 
    web browser. 

* You can see in the ClientService service that I send all requests to the 
  only GatewayService service having address is http://localhost:8080 and 
  the GatewayService service will use the routing rules to forward the client's 
  request to the corresponding service and respond the result from the service 
  back to the corresponding client.

Congratulation, you have implemented successfully the routing function on the 
API gateway and use the API gateway as a single service of receiving requests 
to reduce the complexity at the client side in determining the destination 
address to send a request.
