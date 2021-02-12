package spring.proyecto.gmq.back.models.service;

import java.text.SimpleDateFormat;


import org.springframework.stereotype.Service;

import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 

import spring.proyecto.gmq.back.models.entity.Empleados;

@Service
public class TwilioService {
	// Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "";// ="ACdaad24f7337c3c94ac635b8f747c49f5"; //= "AC2efedef624e638dc8bba27d2820da9d4"; 
    public static final String AUTH_TOKEN = ""; //= "616d969377d77b9d2e55037d18c4abf3"; //= "79ec405d6f321dccf5ae551ac8423b0b"; 
	static String fecha = "dd-MM-yyyy";
	static SimpleDateFormat formatear = new SimpleDateFormat(fecha);
	/*------------------------ SMS ------------------------*/
    public static void sms(Empleados empleado) { 
    	
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+34638248030"),
                new com.twilio.type.PhoneNumber("+14758897855"),
                "Hi there!")
            .create();

        System.out.println(message.getSid());
     
    } 

}
