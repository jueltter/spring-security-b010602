package tech.samagua.spring_security_b010602.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            var securityContext = SecurityContextHolder.getContext();
            var authentication = securityContext.getAuthentication();

            return "Ciao, " + authentication.getName() + "!";
        };

        try(ExecutorService executorService = Executors.newCachedThreadPool();) {
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return executorService.submit(contextTask).get();
        }
    }

    @GetMapping("/hola")
    public String hola() throws Exception {
        Callable<String> task = () -> {
             var securityContext = SecurityContextHolder.getContext();
             var authentication = securityContext.getAuthentication();
            return "Hola, " + authentication.getName() + "!";
        };

        try (ExecutorService executorService = Executors.newCachedThreadPool();
             var e = new DelegatingSecurityContextExecutorService(executorService);){
            return e.submit(task).get();
        }
    }

}
