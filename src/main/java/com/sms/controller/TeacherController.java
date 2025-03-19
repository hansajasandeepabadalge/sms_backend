package com.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.model.Teacher;
import com.sms.service.TeacherService;
import com.sms.service.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

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
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        if (userService.isNotAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
        } else {
            return ResponseEntity.ok(teacherService.addTeacher(teacher));
        }
    }

    // Update existing Teachers' details
    @PutMapping("/teacher/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        if (userService.isNotAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
        } else {
            if (!id.equals(teacher.getTeacherId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " Teacher not found");
            }

            Teacher updatedTeacher = teacherService.updateTeacher(teacher);

            if (updatedTeacher != null) {
                return ResponseEntity.ok(updatedTeacher);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher with ID " + id + " not found");
            }
        }
    }

    // Delete teacher by ID
    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable Long id) {
        if (userService.isNotAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
        } else {
            Teacher teacher = teacherService.getTeacherById(id);

            if (teacher != null) {
                teacherService.deleteTeacher(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Teacher with ID " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher with ID " + id + " not found");
            }
        }
    }
}