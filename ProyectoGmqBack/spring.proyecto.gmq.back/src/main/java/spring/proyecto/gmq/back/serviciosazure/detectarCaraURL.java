package spring.proyecto.gmq.back.serviciosazure;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class detectarCaraURL {

	/*
	 * Creamos un metodo para detectar la cara de la imagen almacenada en el storage,
	 * retornando de esta el id.
	 * Es identica a la clase de detectarCaraArrat, diferenciando que aqui nen vez de 
	 * pasar un octect-stream, pasamos un application-json para que reconozca
	 * la url.
	 * Le pasaremos como parametro la url de la imagen almacenada en la bbdd en lugar de una imagen
	 */
	
	public static String DetectarCaraUrl (String rutaImagen) {
		//Variables de azure, guardamos las credenciales de azure
		final String LlaveAzure = "e4ee36742e6548a3a08689f6b3e9c73b"; //Clave de Azure...
		final String EndpointAzure = "https://recfacialazure.cognitiveservices.azure.com"; //endpoint de Azure...
		
		/*Variable donde convertimos la ruta de la imagen en formato JSON
		 * formato legible para la api de Azure...
		 */
		String Imagen = 	"{\"url\":\"" + rutaImagen + "\"" + "}";
		
		String jsonString = null; //Variable para recoger el JSON que devuelve la api de Azure
		
		String valorIDImagen1 = null; //Variable donde recogemos el id de la imagen
		
		//Creamos un HttpClient
		HttpClient httpClient = HttpClientBuilder.create().build();
		//System.out.println("Hemos creado HTTPClient...");
		
		try {
			
			URIBuilder builder = new URIBuilder(EndpointAzure + "/face/v1.0/detect");
			//System.out.println("Hemos creado un builder con que encuentre la cara en la URL imagen...");
	
			
			//REQUEST parametros
			
			builder.setParameter("detectionModel", "detection_01");
			builder.setParameter("returnFaceId", "true");
			builder.setParameter("returnPersistedFaceId", "true");
			//builder.setParameter("returnFaceLandmarks", "true");
			//builder.setParameter("returnFaceAttributes", "age, gender, facialhair, emotion");
			builder.setParameter("recognitionModel", "recognition_03");
			//builder.setParameter("returnRecognitionModel", "true");
			//builder.setParameter("returnFaceListId", "true");
			
			System.out.println("Hemos establecido que devuelva el ID de la cara...");
			
			//Preparamos URI para la llamada API REST
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			
			System.out.println("Hemos enviado llamada 1 de API REST...");
			
			//RequestHeader
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", LlaveAzure);
						
			//Establecemos el tipo de valor a devolver
			StringEntity reqEntity = new StringEntity(Imagen);
			request.setEntity(reqEntity);
				System.out.println("Se esta leyendo la imagen1...");
				
			
			//Ejecutamos el API REST y obtenemos la respuesta
			HttpResponse responde1 = httpClient.execute(request);
			HttpEntity entity1 = responde1.getEntity();
			System.out.println("Hemos obtenido la priemra respuesta de la imagen 1 (URL)");
			
				if (entity1 != null)
	        	{
	            	// Format and display the JSON response.
	            	System.out.println("REST Response:\n");
	            	jsonString = EntityUtils.toString(entity1).trim();
	            	
	            	JSONArray ArrayJson = new JSONArray(jsonString);
	            	JSONObject obj = ArrayJson.getJSONObject(0);
	            	
	            	valorIDImagen1 = obj.getString("faceId");
	            	
	            	return valorIDImagen1;
	        	}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
				return null;
			}
		
		return null;
	}
}
