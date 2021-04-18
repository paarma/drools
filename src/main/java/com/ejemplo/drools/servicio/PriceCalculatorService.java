package com.ejemplo.drools.servicio;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.drools.modelo.Message;
import com.ejemplo.drools.modelo.ProductPrice;

@Service
public class PriceCalculatorService {

	@Autowired
	private KieContainer kieContainer;
	
	//Se inyecta servicio "PushSubService" para ser agregado como objeto global en la sessión de drools. (Este se usara para simular la publicación de un objeto con kafka desade drools)
	@Autowired
	private PushSubService pushSubService;
	
	public void executeRules(ProductPrice productPrice) {
		KieSession kieSession = kieContainer.newKieSession();
		
		//En este ejemplo agregamos un objeto de forma global a la sessión de drools en este caso el servicio PushSubService.java
		kieSession.setGlobal("publishTool", pushSubService);
		
		
		kieSession.insert(productPrice);
		kieSession.insert(new Message());
		
		kieSession.fireAllRules();
		
		/*
		 * * Libera todos los recursos de la sesión actual, configurando la sesión para la recolección de basura.
		 * * Este método debe ser llamado siempre después de terminar de usar la sesión o el motor.
		 * * no liberará la memoria utilizada por la sesión.
		 * */
		kieSession.dispose();
	}
	
}
