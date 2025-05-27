package com.karu.counsellingsection.user.dtos;

import com.karu.counsellingsection.user.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long userId;

    private String regNo;
    private String annonumousName;
    private String schoolEmail;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String imageUrl;
    private String bio;

    @Enumerated(EnumType.STRING)
    private Role role;
}
