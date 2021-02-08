package spring.proyecto.gmq.back.serviciosazure;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class CompararCaras {
	
	public static String id1, id2; //Variables donde recoger los string
	
	//Variable para reconocer la imagen de la url
	public static detectarCaraURL imagen1;
	
	//Variable para reconocer la imagen del array
	public static detectarCaraArray imagen2;
	
	//Metodo para comparar las caras
	public static String returnIdentical(String image1, String image2) {
		 
		 
		 HttpClient httpclient = HttpClients.createDefault();

	        try
	        {
	            URIBuilder builder = new URIBuilder("https://recfacialazure.cognitiveservices.azure.com/face/v1.0/verify");


	            URI uri = builder.build();
	            HttpPost request = new HttpPost(uri);
	            request.setHeader("Content-Type", "application/json");
	            request.setHeader("Ocp-Apim-Subscription-Key", "e4ee36742e6548a3a08689f6b3e9c73b");
	         
	            id1 = detectarCaraURL.DetectarCaraUrl(image1);
	            System.out.println("id1: "+id1);
	            id2 = detectarCaraArray.DetectarCara(image2);
	            System.out.println("id2: "+id2);
	            StringEntity reqEntity = 
	            		new StringEntity(
	            		"{ \"faceId1\": \""+id1+"\" ,\"faceId2\":\""+id2+"\"}"
	            		);
	           
	            
	            /*
	             * eca78dde-bc13-4fcb-b470-928720021fb9
	             * cdded5e5-528f-4fc0-aa13-7bbaaa7e76dd
	             */
	            request.setEntity(reqEntity);
	        

	            HttpResponse response = httpclient.execute(request);
	            HttpEntity entity = response.getEntity();

	            
	            if (entity != null)
	        	{
	            	
	            	String jsonString = EntityUtils.toString(entity).trim();
	            	if (jsonString.charAt(0) == '[') {
	            	
	                	JSONArray arr = new JSONArray(jsonString);
	                	
	                	JSONObject jsn;
	                	String keyVal = null;
	             
	                	for (int i = 0; i < arr.length(); ++i) {

	                	     jsn = arr.getJSONObject(i);

	                	     keyVal = jsn.getString("confidence");
	                	}
	                	return keyVal;
	                	
	                	
	            	} else if (jsonString.charAt(0) == '{') {
	            	
	                	JSONObject jsonObject = new JSONObject(jsonString);
	                	double prueba = jsonObject.getDouble("confidence");
	                	System.out.println("confidence: "+prueba);
	                	return ""+prueba;
	            	} 
	            	
	        	}
	            }
	            
	   
	       
	            
	        catch (Exception e)
	        {
	        	System.out.println("Error: compareFaces001 "+e.getMessage());
	        	return null;
	        }
	        
			return null;
		 
	 } 
}
