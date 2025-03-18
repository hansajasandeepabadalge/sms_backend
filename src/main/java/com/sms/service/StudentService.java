package com.sms.service;

import com.sms.model.Student;
import com.sms.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Get all the students in the database
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get a one student details from an ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Adding a Student to the database
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Update the existing student details
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    // Delete the existing Student
    public void deleteStudent(Long id) {
        studentRepository.findById(id).map(student -> {
            studentRepository.delete(student);
            return student;
        }).orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    }
}
