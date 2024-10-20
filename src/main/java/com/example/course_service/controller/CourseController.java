package com.example.course_service.controller;

import com.example.course_service.dto.StudentDTO;
import com.example.course_service.entity.Course;
import com.example.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@RequestParam int id) {
        return courseService.findById(id);
    }

    @GetMapping("/{id}/instructor")
    public String getInstructorByCourseId(@RequestParam int courseId) {
        Course course = courseService.findById(courseId);
        int intructor = course.getInstructorId();
    }

    @GetMapping("/{id}/students")
    public List<StudentDTO> getStudents(Integer courseId) {
        String url = "http://localhost:8081/courses/" + courseId + "/students";  // Replace with the actual URL

        ResponseEntity<List<StudentDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {}
        );

        // Return the body of the response (which is the list of DTOs)
        return response.getBody();
    }
}
