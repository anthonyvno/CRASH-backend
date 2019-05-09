package com.realdolmen.EuropeanHub;

import com.realdolmen.EuropeanHub.insurer.Insurer;
import com.realdolmen.EuropeanHub.insurer.InsurerService;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    
    InsurerService insurerService;

    public UserController(InsurerService insurerService) {
        this.insurerService = insurerService;
    }
    
    

    @GetMapping(produces = "application/json")
    @RequestMapping("/login")
    public void login() {
        //return insurerService.findInsurerById(1);
        //return user.getUserName().equals("user") && user.getPassword().equals("password");
    }

    @RequestMapping("/logouts")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
    }

    @RequestMapping("/user")
    public Insurer user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        //return () -> 
        
         String str = new String(Base64.getDecoder().decode(authToken)).split(":")[0];
         System.out.println(str);
         return insurerService.findInsurerByName(str);
    }
}
