package instapay.Modules.Authentication;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class NewUserAuthentication {
    // Twilio credentials
    private static final String ACCOUNT_SID = "AC743d00590eacb66f25f88988dc860299";
    private static final String AUTH_TOKEN = "6785b75bbdbd888350d698f7f6862022";
    private static final String TWILIO_PHONE_NUMBER = "+13072122017";

    private static Map<String, String> phoneToOtpMap = new HashMap<>();

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    // Step 1: Generate OTP
    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Step 2: Send OTP using Twilio
    public void sendOTP(String phoneNumber, String otp) {
        try {
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+" + phoneNumber),
                    new com.twilio.type.PhoneNumber("+" + TWILIO_PHONE_NUMBER),
                    "Your OTP is " + otp
            ).create();

            System.out.println("OTP sent successfully: " + message.getSid());
        } catch (Exception e) {
            System.err.println("Error sending OTP: " + e.getMessage());
        }
    }

    // Step 3: Verify OTP
    public static boolean verifyOTP(String phoneNumber, String enteredOTP) {
        String storedOTP = phoneToOtpMap.get(phoneNumber);
        return storedOTP != null && storedOTP.equals(enteredOTP);
    }

    // Step 4: User Registration
    public static boolean Authenticate(String phoneNumber, String enteredOTP) {
        if (verifyOTP(phoneNumber, enteredOTP)) {
            System.out.println("User registered successfully");
            return true;
        } else {
            System.out.println("Invalid OTP. Registration failed.");
            return false;
        }
    }

    public static void main(String[] args) {
        NewUserAuthentication userService = new NewUserAuthentication();
        String phoneNumber = "+2011******";
        String generatedOTP = userService.generateOTP();
        userService.phoneToOtpMap.put(phoneNumber, generatedOTP);
        userService.sendOTP(phoneNumber, generatedOTP);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter OTP sent to your phone number");
        String enteredOTP = scanner.nextLine();

        userService.Authenticate(phoneNumber, enteredOTP);
    }


}
