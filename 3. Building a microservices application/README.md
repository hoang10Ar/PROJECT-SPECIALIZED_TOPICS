~ BUILDING A SMALL MICROSERVICES APPLICATION ~

* I am going to build two business services (normal services) are 
  a Student service (manage students’ information) and a Score 
  service (manage students’ score). The Student service will 
  communicate with the Score service to get its student scores. 

* After running the ScoreService service, if you navigate to
  http://localhost:9999/scores, you will see a list of scores and
  just students' ids. If you navigate to 
  http://localhost:9999/scores/S2, you will see the score of the 
  student having id S2.

* After running the ScoreService and the StudentService service, 
  if you navigate to http://localhost:8888/students, you will see 
  a list of students and their scores which are managed by the 
  ScoreService service and the StudentService service have just
  communicated with the ScoreService service to get data.

Congratulations! You have just developed a small microservices web 
application with Spring. Those two services can be deployed in 
different servers, connecting different databases and implemented 
using different technologies.