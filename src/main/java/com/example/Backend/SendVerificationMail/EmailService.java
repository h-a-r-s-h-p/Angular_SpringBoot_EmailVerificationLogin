package com.example.Backend.SendVerificationMail;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.example.Backend.IsDomainExist.*;

@Service
public class EmailService extends EmailAbstractClass
{
    @Autowired 
    private JavaMailSender mailSender;
    @Autowired
    private DomainExistInterface isDomainExist = new DomainService();
    
    String OTP;

    public String genOTP(){
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

    
    public String sendOTPEmail(String emailAddr) throws MessagingException {
        String domain = isDomainExist.getDomain(emailAddr);
        try {
            isDomainExist.checkIfDomainExist( domain );
            OTP=genOTP();
            OTPofEachUser.put(emailAddr, OTP);
            MimeMessage simpleMail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(simpleMail);
            helper.setTo(emailAddr);
            helper.setFrom("bootspring4@gmail.com");
            String Content = " <p> Hello "+ emailAddr + "</p>"
                            + "<p> Your OTP for wisestep login is: </p>"
                            + "<h1>         " + this.OTP +"</h1>"
                            + "<p> Thank You <br>  Wisestep </p>";
            helper.setSubject("Wisestep Login");
            helper.setText(Content,true);
            mailSender.send(simpleMail);
            
            return this.OTP;
        }
        catch( Exception e ) {
            System.out.println(e);
            return null;
        }
        
    }
        
}
