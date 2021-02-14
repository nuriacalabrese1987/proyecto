package spring.proyecto.gmq.back.models.service;

import java.text.SimpleDateFormat;


import org.springframework.stereotype.Service;

import com.twilio.Twilio; 
import com.twilio.rest.api.v2010.account.Message; 

import spring.proyecto.gmq.back.models.entity.Empleados;

@Service
public class TwilioService {
	// Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "ACdaad24f7337c3c94ac635b8f747c49f5"; 
    public static final String AUTH_TOKEN = "d2fa53eb1d97386bfe8a3b2769695144"; 
	static String fecha = "dd-MM-yyyy";
	static SimpleDateFormat formatear = new SimpleDateFormat(fecha);
	/*------------------------ SMS ------------------------*/
    public static void sms(Empleados empleado) { 
    	
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+34"+empleado.getTelefono()),
                new com.twilio.type.PhoneNumber("+14758897855"),
                "Su contaseña es: "+empleado.getToken())
            .create();

        System.out.println(message.getSid());
     
    } 

}
