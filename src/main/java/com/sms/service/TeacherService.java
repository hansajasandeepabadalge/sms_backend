package com.sms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.model.Teacher;
import com.sms.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    // This is for testing purposes to test json outputs
    public void testingJson(List<?> a) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(a);
            System.out.println(json);
        } catch (Exception e) {
            System.out.println("Error occurred while converting to JSON: " + e.getMessage());
        }
    }

    // Get all the teachers list from the Database
    public List<Teacher> getAllTeachers() {
        // testingJson(teacherRepository.findAll());
        return teacherRepository.findAll();
    }

    // Get a one teacher's details from ID
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }
}
