package com.example.course_service.controller;

import com.example.course_service.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "student", url = "localhost:8087")
public interface Client {

    @RequestMapping(method = RequestMethod.GET, value = "sayed/{id}/students")
    List<StudentDTO> getStudents(@PathVariable int id);
}
