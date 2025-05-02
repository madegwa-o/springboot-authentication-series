package com.example.FullBackend.course;


import com.example.FullBackend.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;

    private String courseName;
    private String courseCode;
    private String courseDescription;

    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    private List<User> user;
}
