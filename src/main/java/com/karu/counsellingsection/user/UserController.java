package com.karu.counsellingsection.user;


import com.karu.counsellingsection.user.dtos.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final PagedResourcesAssembler<UserDto> pagedResourcesAssembler;

    @GetMapping("/by-role")
    public PagedModel<?> getUsersByRoles(@RequestParam Role role,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size) {

      //  Page<User> userPage = userService.getUsersByRoles(roles,PageRequest.of(page, size));
        Page<UserDto> userPage = userService.getUsersByRole(role, PageRequest.of(page, size));
        return  pagedResourcesAssembler.toModel(userPage);
    }


}
