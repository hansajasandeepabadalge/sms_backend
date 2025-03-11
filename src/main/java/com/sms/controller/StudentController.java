    package com.sms.controller;

    import com.sms.model.Student;
    import com.sms.service.StudentService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/v1")
    @CrossOrigin(origins = "http://localhost:4200")
    public class StudentController {

        @Autowired
        private StudentService studentService;

        @GetMapping("/students")
        public List<Student> getAllStudents() {
            return studentService.getAllStudents();
        }

        @GetMapping("/student/{id}")
        public ResponseEntity<?> getStudentById(@PathVariable Long id) {
            Student student = studentService.getStudentById(id);

            if (student != null) {
                return ResponseEntity.ok(student);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found " + id + " not found");
            }
        }

        @PostMapping("/students")
        public Student createStudent(@RequestBody Student student) {
            return studentService.addStudent(student);
        }

        @PutMapping("/student/{id}")
        public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
            if (!id.equals(student.getStudentId())) {
                return ResponseEntity.badRequest().body("Path ID and request body ID do not match" + id + " " + student.getStudentId());
            }
            Student updatedStudent = studentService.updateStudent(student);
            if (updatedStudent != null) {
                return ResponseEntity.ok(updatedStudent);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID " + id + " not found");
            }
        }

        @DeleteMapping("/student/{id}")
        public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                studentService.deleteStudent(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student with ID " + id + " deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID " + id + " not found");
            }
        }
    }
