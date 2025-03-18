package com.sms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    private String phoneNumber;

    private String subject;

    @JsonBackReference
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Classroom> classes = new ArrayList<>();

//    @Override
//    public String toString() {
//        return "id: " + teacherId + ", name: " + firstName;
//    }
}
