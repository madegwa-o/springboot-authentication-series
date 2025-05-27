package com.karu.counsellingsection.user;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.karu.counsellingsection.articles.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String regNo;
    private String annonumousName;
    private String password;
    private String schoolEmail;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean enabled = false;
    private String imageUrl;
    private String bio;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Article> article = new ArrayList<>();


}
