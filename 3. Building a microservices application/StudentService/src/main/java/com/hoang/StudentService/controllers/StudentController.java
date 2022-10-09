package com.hoang.StudentService.controllers;

import com.hoang.StudentService.models.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StudentController {
    // Fake data, a list of students don't include their scores
    private Student[] students = {
            new Student("S1", "An"),
            new Student("S2", "Bảo"),
            new Student("S3", "Cường")
    };

    // Accept HTTP GET requests at http://localhost:8888/students and respond the
    // list of students having full information include their scores
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Student[] getAllStudents() {
        RestTemplate restTemplate = new RestTemplate(); // Generate HTTP request
        for(var i = 0; i < students.length; i++) {
            Student student = students[i];

            // The API endpoint to get this student's score
            String url = "http://localhost:9999/scores/student/" + student.getId();

            // Send a request to the ScoreService service to get this student's score
            int score = restTemplate.getForObject(url, Integer.class);
            student.setScore(score);
        }

        return students;
    }
}
