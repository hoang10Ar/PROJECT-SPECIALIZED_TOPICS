~ AN EXAMPLE OF APPLYING THE "Circuit breaker" PATTERN ~

* The library / project used to apply the pattern: Resilience4j.

* I will create a FailedService service representing a service 
  that often fails and responds error when receiving requests
  and a ClientService service which calls the FailedService 
  service and handles error when it occurs.

* After running the FailedService service, if you navigate to
  http://localhost:8081/data, you can see the response string
  "Get data successfully".

* About the configuration for the circuit breaker of the 
  Resilience4j project, you will provide a unique name for a
  method that apply the circuit breaker pattern, and you will
  declare the circuit breaker's configuration information for
  that method like the following structure in your YAML file:
      resilience4j:
        circuitbreaker:
          instances:
            <circuit breaker's name applied this information>:
              <many configuration information>
            <another circuit breaker's name>:
              <many configuration information>

* Some configuration properties in the circuit breaker are:
  - slidingWindowSize: the number of calls to a remote service
    that the circuit breaker based on to decide whether to switch
    from the Closed state to the Open state.
  - slidingWindowType: if the sliding window is TIME_BASED, the
    circuit breaker will base on responses received of requests
    sent in the last "slidingWindowSize" seconds to calculate the
    failure rate and switch to the Open state. If the sliding
    window is COUNT_BASED, the circuit breaker will base on 
    responses received of the last "slidingWindowSize" requests
    sent to calculate the failure rate after at least 
    "minimumNumberOfCalls" requests sent.
  - minimumNumberOfCalls: the minimum number of call to a remote
    service for the circuit breaker to calculate the failure rate.
    For example, if this value is 5, even though you have sent 4
    requests to the remote service and all 4 responses received 
    are failed, the circuit breaker still will not calculate the 
    failure rate until at least 5 requests have been sent.
  - failureRateThreshold: the failure rate threshold in percentage.
    If the circuit breaker sees that the failure rate in reality is
    larger than this rate, it will switch the method applying the 
    pattern from the Closed state, which can send requests to the 
    remote service normally, to the Open state, which refuses all
    requests sent to the remote service, for a period of time and
    call the fallback method to respond to each request sent to the
    failed service.
  - waitDurationInOpenState: the total time that the circuit breaker
    will be in the Open state before switching to the Half-open 
    state, which only "permittedNumberOfCallsInHalfOpenState" 
    requests are allowed to be sent to the failed service and the 
    rest requests will be sent to the fallback method.
  - permittedNumberOfCallsInHalfOpenState: the number of requests
    allowed to be sent to the failed service while in the Half-open
    state and the circuit breaker will base on responses received of
    those requests to decide to switch to the Closed or Open state.
  
* After running the FailedService and ClientService service, you can
  test your services by doing the following step: 
  1. If you navigate to http://localhost:8080, you can see the 
     response string "Get data successfully" in your browser and 
     your terminal is like the below result:
     #--------------------------------------------------------#
     #    Connect to the FailedService service successfully   #
     #--------------------------------------------------------#
     => Implement two services successfully.
  2. Let's stop the FailedService service. If you navigate to 
     http://localhost:8080 for the second and the third time, you 
     will see the following result in your terminal:
     #--------------------------------------------------------#
     #    Connect to the FailedService service successfully   #
     #    Connect to the FailedService service failed         #
     #    Connect to the FailedService service failed         #
     #--------------------------------------------------------#
     => Implement the fallback method successfully.
  3. After sending the first three request at step 2 above, if you
     refresh your browser for more than twice, run the FailedService
     service again and refresh your browser for many times, the 
     result in your terminal is similar as below:
     #--------------------------------------------------------#
     #    Connect to the FailedService service successfully   # ----|  At the beginning, the circuit 
     #    Connect to the FailedService service failed         #     |  breaker is in the Closed state. 
     #    Connect to the FailedService service failed         #     |- After 5 requests, the failure
     #    Connect to the FailedService service failed         #     |  rate is 4/5 = 80% > 50%.
     #    Connect to the FailedService service failed         # ----|    
     #    OPEN state. Please wait 20s                         # -|  After switching to the Open state,
     #    OPEN state. Please wait 20s                         #  |  the circuit breaker will call the
     #    OPEN state. Please wait 20s                         #  |  fallback method to respond any
     #    OPEN state. Please wait 20s                         #  |- requests sent to the FailedService
     #    OPEN state. Please wait 20s                         #  |  service for a period of 20 seconds
     #    OPEN state. Please wait 20s                         #  |  even if the FailedService service is 
     #    OPEN state. Please wait 20s                         # -|  running again.
     #    Connect to the FailedService service successfully   # --|  After sending 3 successful requests 
     #    Connect to the FailedService service successfully   #   |- in the Half-open state, the failure
     #    Connect to the FailedService service successfully   # --|  rate is 0/3 = 0% < 50%.
     #    Connect to the FailedService service successfully   # -| 
     #    Connect to the FailedService service successfully   #  |- The circuit breaker switch back to
     #    Connect to the FailedService service successfully   # -|  the Closed state.
     #--------------------------------------------------------#
  4. After sending the first three request at step 2 above, if you
     refresh your browser for more than twice, still stop the
     FailedService service and refresh your browser for many times,
     the result in your terminal is similar as below:
  #--------------------------------------------------------#
  #    Connect to the FailedService service successfully   # ----|  At the beginning, the circuit
  #    Connect to the FailedService service failed         #     |  breaker is in the Closed state.
  #    Connect to the FailedService service failed         #     |- After 5 requests, the failure
  #    Connect to the FailedService service failed         #     |  rate is 4/5 = 80% > 50%.
  #    Connect to the FailedService service failed         # ----|
  #    OPEN state. Please wait 20s                         # -|  
  #    OPEN state. Please wait 20s                         #  |  
  #    OPEN state. Please wait 20s                         #  |  The circuit breaker switches to 
  #    OPEN state. Please wait 20s                         #  |- the Open state for a period of 
  #    OPEN state. Please wait 20s                         #  |  20 seconds and the FailedService
  #    OPEN state. Please wait 20s                         #  |  is still off.
  #    OPEN state. Please wait 20s                         # -|  
  #    Connect to the FailedService service failed         # --|  After sending 3 failed requests
  #    Connect to the FailedService service failed         #   |- in the Half-open state, the 
  #    Connect to the FailedService service failed         # --|  failure rate is 3/3 = 100% > 50%.
  #    OPEN state. Please wait 20s                         # -|
  #    OPEN state. Please wait 20s                         #  |- The circuit breaker switch back to
  #    OPEN state. Please wait 20s                         # -|  the Open state for 20 seconds.
  #--------------------------------------------------------#
  
Congratulations! You have just applied the circuit breaker pattern 
to your service to deal with fails that occur when it calls a 
remote service. Instead of returning the error to the user, it will
call the fallback method to returning another meaningful result to 
the user. Instead of repeatedly sending requests to the failed 
service, it will temporarily disconnect from your service to the 
failed service for a little time, so it can give the failed service
have time to recover.


