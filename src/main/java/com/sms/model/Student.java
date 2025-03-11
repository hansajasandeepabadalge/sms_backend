package com.sms.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sms.enums.Gender;
import com.sms.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String parentName;

    private String parentEmail;

    private String parentPhone;

    private String address;

    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classId", nullable = false )
    private Classroom classroom;
}