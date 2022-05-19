package com.example.Backend.SendVerificationMail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

public abstract class EmailAbstractClass {
    public Map<String, String> OTPofEachUser= new HashMap<>();
    abstract public String genOTP();
    abstract public String sendOTPEmail(String emailAddr) throws MessagingException;
}
