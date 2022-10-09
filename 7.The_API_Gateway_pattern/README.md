~ AN EXAMPLE OF APPLYING THE "API Gateway" PATTERN ~

* The library / project used to apply the pattern: Spring Cloud
  Gateway

* You will provide a list of rules or routes to determine which 
  requests will be sent to which services and how you want to 
  modify requests and responses. When you use the Spring Cloud
  Gateway project, you can declare many routes like the following
  structure in your YAML file:
      spring:
        cloud:
          gateway:
            routes:
            - id: <a unique name for this route>
              uri: <a location of the service receiving requests>
              predicates: (some conditions that the request have  
                          to satisfy to be sent to the uri above)
              - <condition's name>=<value>
              - <condition's name>=<value>
              ...
              filters: (some modification to the request and response)
              - <filter name>=<value>
              ...

            - id: <another route>
              uri: ...
              predicates:
              ...
              filters:
              ...

    - A route is defined by an id, a destination uri, a list of
      predicates, and a list of filters (optional). A route is matched
      if the list of predicates are all true. You will use predicates
      to decide which requests will be sent to which destination uri.
    - The uri can be a single physical address such as
      http://192.168.1.2:8888 or can be a logical name using load
      balancing algorithm if your API gateway is registered with the
      Eureka server such as lb://Service1.
    - There are some common built–in predicates which you can use in
      the Spring Cloud Gateway project (you can see the full list of
      built–in predicates at https://docs.spring.io/spring-cloud-
      gateway/docs/current/reference/html/#gateway-request-predicates-
      factories) such as:
        + After: the After predicate takes one parameter, a datetime
          value. This predicate matches any requests that are sent after
          that datetime value.
        + Header: the Header predicate takes two parameters, a header
          name (a field) in the HTTP header of the request and a Java
          regular expression. This predicate matches any requests have
          that header name whose value matches the regular expression.
        + Path: the Path predicate can take one parameter, a PathPattern
          pattern string. For example: the pattern string "/account" matches
          any requests having the "/account" path but not "/account/1",
          the pattern string "/account/*" matches "/account/1" but not
          "/account/1/2", the pattern string "/account/**" matches
          "/account/1" as well as "/account/1/2",… (you can see the full
          information about the PathPattern at https://docs.spring.io/spring-
          framework/docs/current/javadoc-api/org/springframework/web/util/
          pattern/PathPattern.html).
    - Filters are components in the Spring Cloud Gateway project that allows 
      you to modify HTTP requests and HTTP responses. A filter is attached
      with a route or many routes. The Spring Cloud Gateway project provides
      many built-in filters, you can also create your own filters too. There 
      are some common built–in filters which you can use (you can see the full 
      list of built–in filters at https://docs.spring.io/spring-cloud-gateway/
      docs/current/reference/html/#gatewayfilter-factories) such as:
        + AddRequestHeader: the AddRequestHeader filter takes two parameters: 
          the HTTP request’s header name and the header’s value. It will add 
          that “name:value” pair in the request’s header.
        + AddRequestParameter: the AddRequestParameter filter takes two
          parameters: the HTTP request’s parameter name and its value (for 
          example, the request to “http://example.org?id=1” has the “id” 
          parameter and the “1” value). It will add that “name=value” pair in 
          the request’s query string.
        + RewritePath: the RewritePath filter takes two parameters: an old 
          string will be replaced and a new string will replace that old string. 
          It will rewrite any part in the request’s path which matches the old 
          string into the new string. For example: the “RewritePath=a, b” will 
          replace any “a” string with the “b” string, replace the “/api/abc” 
          path with the “bpi/bbc” path.

* I have implemented three examples are:
  - A microservices application doesn't use the API Gateway.
  - A microservices application uses the API Gateway and predicate components to
    perform the routing function.
  - A microservices application uses the API Gateway, predicate and filter 
    components to perform the modification requests and responses function.