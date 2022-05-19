package com.example.Backend.OTPVerification;

public interface OTPVerificationInterface {
    public boolean verifyOTPs(String OTPfromUser, String emailAddr);
}
