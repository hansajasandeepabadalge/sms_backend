package com.sms.controller;

import com.sms.model.Student;
import com.sms.model.Teacher;
import com.sms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Get all the teachers
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // Get a teacher by ID
    @GetMapping("/teacher/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);

        if (teacher != null) {
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " Teacher not found");
        }
    }

    // Adding a new Teacher
    @PostMapping("/teacher")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    // Update existing Teachers' details
    @PutMapping("/teacher/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        if (!id.equals(teacher.getTeacherId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " Teacher not found");
        }
        // Getting Updated Teacher details
        Teacher updatedTeacher = teacherService.updateTeacher(teacher);

        //  and give a response
        if (updatedTeacher != null) {
            return ResponseEntity.ok(updatedTeacher);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID " + id + " not found");
        }
    }

    // Delete teacher by ID
    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);

        // Check the ID related teacher is in the database or not and delete
        if (teacher != null) {
            teacherService.deleteTeacher(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Teacher with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher with ID " + id + " not found");
        }
    }
}
