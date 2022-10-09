~ AN EXAMPLE OF APPLYING THE "Externalized Configuration" PATTERN ~

* The library / project used to apply the pattern: Spring Cloud Config

* I am going to create two business services are a Student 1 service,
  a Student 2 service and a config server service which connects to a
  GitHub repository to store Student 1 service and Student 2 service
  configuration files.

* I have created a repository https://github.com/ben595/MyConfigServer
  in GitHub having two property files are:
  - application.properties:
    ##############################################################
    #                                                            #
    #  propertyFileName=This is the application.properties file  #
    #                                                            #
    ##############################################################
  - Student1Service.properties:
    ##################################################################
    #                                                                #
    #  propertyFileName=This is the Student1Service.properties file  #
    #                                                                #
    ##################################################################
  - There is no Student2Service.properties file in the GitHub repository.

* After running the config server, you can check which properties information
  is applied for what service by navigating to http://<config server address>/
  <service name>/default.
  - If you navigate to http://localhost:8080/Student1Service/default, you can
    see that the Student 1 service read the Student1Service.properties and the
    application.properties file.
  - If you navigate to http://localhost:8080/Student2Service/default, you can
    see that the Student 2 service only read the application.properties file.
  - If you navigate to http://localhost:8080/abc/default, you can see that 
    this "abc" service (non-exist) will read the application.properties file.
  => If you want to provide configuration information to all services, you can
     write your data in the application.properties file. If you want to provide 
     configuration information to a specific service, you can write your data in 
     the <service's name>.properties file. The <service's name>.properties file 
     has a higher priority than the application.properties file, so it will 
     override any duplicate properties exist in the application.properties file.

* After running the Student 1 service and the config server, if you navigate to 
  http://localhost:8081/fileName, the "This is the Student1Service.properties
  file" string will be displayed in your browser => It has read the configuration
  information storing in the GitHub repository successfully.

* After running the Student 2 service and the config server, if you navigate to
  http://localhost:8082/fileName, the "This is the application.properties file" 
  string will be displayed in your browser => It has read the configuration
  information storing in the GitHub repository successfully.

Congratulations! You have just created a config server and centralized some 
configuration information of services. Services can connect to the config server
to read information. The management of configuration information of services has
become easier.
  