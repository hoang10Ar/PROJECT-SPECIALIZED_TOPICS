~ BUILDING A RESTFUL WEB SERVICE EXPOSING MANY API ENDPOINTS ~

* I have created a StudentService service. After running the main 
  method, you will see a lot of logging information. There is the 
  "... Tomcat started on port(s): 8888 ..." string => Now you can 
  send requests to localhost:8888 (StudentService service address).

* I add an extra property information is “my_greeting=Hello, you are”. 

* I have created a new class named StudentController.java in the 
  project. It will accept HTTP GET requests at 
  http://localhost:8888/home/<any value> and respond the 
  "<my_greeting property value> <any value>" string. After running the 
  service, visit http://localhost:8888/home/hoang, you should see the
  "Hello, you are hoang" string on your browser.

Congratulations! You have just developed a RESTful web service with 
the Spring framework.