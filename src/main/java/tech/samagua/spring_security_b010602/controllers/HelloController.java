package tech.samagua.spring_security_b010602.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {

        //SecurityContext securityContext = SecurityContextHolder.getContext();
        //var authentication = securityContext.getAuthentication();

        return "Hello, " +authentication.getName()+ "!";
    }

}
