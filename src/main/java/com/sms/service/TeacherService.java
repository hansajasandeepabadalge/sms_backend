package com.sms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.model.Teacher;
import com.sms.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    // Get all the teachers list from the Database
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Get a one teacher's details from ID
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    // Create a new teacher
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Delete the existing Teacher
    public void deleteTeacher(Long id) {
        teacherRepository.findById(id).map(teacher -> {
            teacherRepository.delete(teacher);
            return teacher;
        }).orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    }
}
