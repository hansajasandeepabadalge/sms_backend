package com.sms.service;

import com.sms.model.Classroom;
import com.sms.repository.ClassroomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    // Find all Classrooms
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    // Check the classroom by ID
    public Classroom getClassroomById(Long id) {
        return classroomRepository.findById(id).orElse(null);
    }

    // Adding a new classroom
    public Classroom addClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    // Update the existing classroom details
    public Classroom updateClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    // Delete the existing Classroom
    public void deleteClassroom(Long id) {
        classroomRepository.findById(id).map(classroom -> {
            classroomRepository.delete(classroom);
            return classroom;
        }).orElseThrow(() -> new EntityNotFoundException("Classroom not found with ID: " + id));
    }
}

