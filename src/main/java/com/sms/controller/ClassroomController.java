package com.sms.controller;

import com.sms.model.Classroom;
import com.sms.service.ClassroomService;
import com.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ClassroomController {
    
    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private UserService userService;

    // Get all the classroom in the database
    @GetMapping("/classrooms")
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    // Get a classroom details related to the ID
    @GetMapping("/classroom/{id}")
    public ResponseEntity<?> getClassroomById(@PathVariable Long id) {
        Classroom classroom = classroomService.getClassroomById(id);
        if (classroom != null) {
            return ResponseEntity.ok(classroom);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom not found " + id + " not found");
        }
    }

    // Create a new classroom
    @PostMapping("/classroom")
    public ResponseEntity<?> createClassroom(@RequestBody Classroom classroom) {
        if (userService.isNotAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
        } else {
            return ResponseEntity.ok(classroomService.addClassroom(classroom));
        }
    }

    // Update existing classrooms' details
    @PutMapping("/classroom/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Classroom classroom) {
        if (userService.isNotAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
        } else {
            if (!id.equals(classroom.getClassId())) {
                return ResponseEntity.badRequest().body("Path ID and request body ID do not match" + id + " " + classroom.getClassId());
            }
            // Getting Updated classroom details
            Classroom updatedClassroom = classroomService.updateClassroom(classroom);

            //  and give a response
            if (updatedClassroom != null) {
                return ResponseEntity.ok(updatedClassroom);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom with ID " + id + " not found");
            }
        }
    }

    // Delete classroom by ID
    @DeleteMapping("/classroom/{id}")
    public ResponseEntity<?> deleteClassroomById(@PathVariable Long id) {
        if (userService.isNotAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
        } else {
            Classroom classroom = classroomService.getClassroomById(id);

            // Check the ID related classroom is in the database or not and delete
            if (classroom != null) {
                classroomService.deleteClassroom(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Classroom with ID " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classroom with ID " + id + " not found");
            }
        }
    }
}
