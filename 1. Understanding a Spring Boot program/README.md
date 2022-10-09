~ UNDERSTANDING A SPRING BOOT PROGRAM ~

* Spring Initializr is a website for setting up Spring Boot
  projects in an easier and faster way. You can access Spring 
  Initializr at  https://start.spring.io. 

* After pressing “GENERATE”, a compressed file will be downloaded 
  which is an archive of a web application that is configured with 
  your choices.

* If you run the main method, you will see a lot of information
  displayed on your terminal. That is system notes for you to know
  more status of your application. You will see the "... Tomcat 
  started on port(s): 8080 (http) ..." string on your terminal. Your
  program is running on port 8080 (by default).

* Spring Boot allows you to provide configuration information to the 
  application through external files. You can use properties files, 
  YAML files,... Some common properties are:
  - server.port: provide a server port (maybe already in use in your 
    computer).  
  - spring.application.name: provide a name for the application.
  
* Application.properties and application.yml file (if any) are
  considered properties file read by Spring Boot. You can create your 
  own property name and use its value later in your program.
  - Syntax used in the *.properties file is:
       "<property name>=<property value>".
  - Property name can have multiple layers and can be accessed through 
    the " . " character. For example: "spring.application.name",
    "spring.profiles.active",...
  - Syntax used in the *.yml file is:
        <property name layer 1>:
          <property name layer 2>:
            <property name layer ...>:
              <property name layer n>: <property value>
  
       <another property name layer 2>:
         <another property name layer ...>:
           <another property name layer m>: <another property value> 
  - For example, these two files shown below is the same:
    + The *.properties file:  #----------------------------------#
                              #  server.port=8080                #
                              #                                  #
                              #  spring.application.name=Hoang   #
                              #  spring.profile.active=dev       #
                              #----------------------------------#
    + The *.yml file:     #--------------------#
                          #  server:           #
                          #    port: 8080      #
                          #                    #
                          #  spring:           #
                          #    application:    #
                          #      name: Hoang   #
                          #    profile:        #
                          #      active: dev   #
                          #--------------------#
