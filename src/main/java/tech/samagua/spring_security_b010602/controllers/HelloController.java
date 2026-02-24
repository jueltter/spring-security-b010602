package tech.samagua.spring_security_b010602.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {

        //SecurityContext securityContext = SecurityContextHolder.getContext();
        //var authentication = securityContext.getAuthentication();

        return "Hello, " +authentication.getName()+ "!";
    }

    @GetMapping("/bye")
    @Async
    public void bye() {
        var securityContext = SecurityContextHolder.getContext();
        var authentication = securityContext.getAuthentication();


        log.info("Goodbye, {}!", authentication.getName());
    }

}
