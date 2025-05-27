package com.karu.counsellingsection.user;

import com.karu.counsellingsection.user.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<UserDto> getUsersByRole(Role role, Pageable pageable) {
        // Fetch users by role
        Page<User> users = userRepository.findByRole(role, pageable);

        // Map User entities to UserDto
        return users.map(user -> UserDto.builder()
                .userId(user.getUserId())
                .regNo(user.getRegNo())
                .annonumousName(user.getAnnonumousName())
                .schoolEmail(user.getSchoolEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .imageUrl(user.getImageUrl())
                .bio(user.getBio())
                .role(user.getRole())
                .build());
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.getUserByRegNoBeforeOrSchoolEmail(username,username);
    }

    public Optional<User> findByUserId(Long authorId) {
        return userRepository.findById(authorId);
    }
}
