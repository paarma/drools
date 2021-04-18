package com.ejemplo.drools.servicio;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Ejemplo de servicio que simula la publicación de un objeto con kafka
 * @author pablo.manquillo
 *
 */
@Service
public class PushSubService {
	
	 //@Autowired
	 //private KafkaTemplate<String, String> kafkaTemplate;
	
	public void publishNewProductCreated(Object o) throws JsonProcessingException {
		String rawJSON = new ObjectMapper().writeValueAsString(o);
		
		//kafkaTemplate.send("newProduct", rawJSON); ...
		
		System.out.println("************Publishing newProduct Topic , content ["+rawJSON+"]");
	}
	
}
