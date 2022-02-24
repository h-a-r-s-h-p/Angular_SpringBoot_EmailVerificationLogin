package com.example.Backend.OTPVerification;
import com.example.Backend.SendVerificationMail.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service                                                                    // All classes where a function is defined and is used by controller must be annotated with @service to access these fucntion
public class OTPVerificationService implements OTPVerificationInterface{
    @Autowired                                                              // Whenever any object is created it must be annotated with autowired
    private EmailAbstractClass sendMail = new EmailService();

    public boolean verifyOTPs(String OTPfromUser, String emailAddr){
        String realOTPofUser = sendMail.OTPofEachUser.get(emailAddr);
        if(OTPfromUser.equals(realOTPofUser)) {
            return true;
        }
        else return false;
    }
}
