package com.example.Backend.OTPVerification;
import com.example.Backend.SendVerificationMail.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPVerificationService implements OTPVerificationInterface{
    @Autowired
    private EmailAbstractClass sendMail = new EmailService();

    public boolean verifyOTPs(String OTPfromUser, String emailAddr){
        String realOTPofUser = sendMail.OTPofEachUser.get(emailAddr);
        if(OTPfromUser.equals(realOTPofUser)) {
            return true;
        }
        else return false;
    }
}
