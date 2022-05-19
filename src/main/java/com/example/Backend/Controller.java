package com.example.Backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.example.Backend.OTPVerification.OTPVerificationInterface;
import com.example.Backend.SendVerificationMail.EmailAbstractClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("")
public class Controller {
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailAbstractClass sendMailMethods;

    @Autowired
    private OTPVerificationInterface verificationInterface;

    @GetMapping("/all")                          // no body is taken by get request
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public Users createUser(@RequestBody String emailAddr){
        Users user = new Users();
        user.setEmail(emailAddr);
        return userRepository.save(user);
    }

    @GetMapping("/{email}")
    public boolean userLoggedIn(@PathVariable String email){   // returns null if the email doesnot exist in the database
        Users user = userRepository.findByemail(email);
        if(user==null) return false;
        return true;
    }

    @DeleteMapping("/deleteduser/{emailAddr}")                                                        // The parameter must be provided with the path, because delete request ignores whatever we provide in the body
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable String emailAddr){
        Users temp = userRepository.findByemail(emailAddr);
        userRepository.delete(temp);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mail/{email}")
    public int sendMail(@PathVariable String email) throws MessagingException{
        String OTP="" ;
        OTP = sendMailMethods.sendOTPEmail(email);
        System.out.println("The OTP response from the server is : "+ OTP);
        if(OTP == null) return 0;
        return 1;
    }

    @PostMapping("/verify/{emailAddr}")
    public boolean getOTPAndVerify(@RequestBody String OTPfromUser, @PathVariable String emailAddr){
        return verificationInterface.verifyOTPs(OTPfromUser, emailAddr);
    }

} 
