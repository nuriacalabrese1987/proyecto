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
    
    /*
    //------------------------ WhatsApp ------------------------
    public static void whatsApp(Empleados empleado) { 

    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator( 	
                new com.twilio.type.PhoneNumber("whatsapp:+34"+empleado.getTelefono()), 
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),  
                "A la atencion de: "+empleado.getNombre()+" "+empleado.getApellidos()+". Se ha programado una visita el "+formatear.format(empleado.getFecha_visita())+
                ". Para finalizar su registro debe acceder a localhost:4200/registro/"+empleado.getId_empleado()+
                ". Atentamente: Atos Iberia"
                )     
            .create(); 
        System.out.println(message.getSid()); 
    }
    */

    /*
    
    //------------------------EMAIL--------------------------------
	private static final Logger LOGGER = LoggerFactory.getLogger(TwilioService.class);
	@Autowired
	private JavaMailSender sender;


	boolean sendEmailTool(Empleados empleado) {
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String mensaje = "A la atencion de: "+empleado.getNombre()+" "+empleado.getApellidos()+". "
				+ "\n Se ha programado una visita en nuestra sede para la fecha: "+formatear.format(empleado.getFecha_visita())
				+ "\nPara finalizar su registro debe acceder a la web:\n"
				+ " http://localhost:4200/registro/"+empleado.getId()+
				"\n Atentamente: Dto de atencion al cliente";
		
		try {
			helper.setTo(empleado.getEmail());
			helper.setText(mensaje, true);
			helper.setSubject("Finalice su registro");
			sender.send(message);
			send = true;
			LOGGER.info("Mail enviado!");
			LOGGER.info(mensaje);
		} catch (MessagingException e) {
			LOGGER.error("Hubo un error al enviar el mail: {}", e);
		}
		return send;
	}
	*/
}
