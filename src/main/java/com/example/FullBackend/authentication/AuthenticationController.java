package com.example.FullBackend.authentication;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/custom-error")
    public String error() {
        return "error";
    }

    @GetMapping("/custom-success")
    public String success() {
        return "success";
    }

    @GetMapping("/custom-login")
    public String login() {
        return "login";
    }

    @PostMapping("/submission")
    public String submit(@RequestParam String username, @RequestParam String password) {
        return "login";
    }


//    how spring security will work
//    @PostMapping("/submission")
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
//
//        logger.info("username={}, password={}", username, password);
//        System.out.println("redirecting to dashboard");
//        return "redirect:/dashboard";
//    }


}
