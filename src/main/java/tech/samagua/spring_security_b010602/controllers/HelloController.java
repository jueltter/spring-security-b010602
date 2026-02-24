package tech.samagua.spring_security_b010602.controllers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        var authentication = securityContext.getAuthentication();

        return "Hello, " +authentication.getName()+ "!";
    }

}
