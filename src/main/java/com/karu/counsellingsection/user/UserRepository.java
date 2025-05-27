package com.karu.counsellingsection.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.regNo = ?1 OR u.schoolEmail = ?2")
    Optional<User> getUserByRegNoBeforeOrSchoolEmail(String regNo, String schoolEmail);

    Optional<User> findBySchoolEmail(String email);

    Page<User> findByRole(Role role, Pageable pageable);

    User findUserByRegNo(String regNo);

//    @Query("SELECT u FROM User u WHERE :role MEMBER OF u.roles")
//    Page<User> findByRole(Role role, Pageable pageable);


}
