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
	
	//Se inyecta servicio "PushSubService" para ser agregado como objeto global en la sessi�n de drools. (Este se usara para simular la publicaci�n de un objeto con kafka desade drools)
	@Autowired
	private PushSubService pushSubService;
	
	public void executeRules(ProductPrice productPrice) {
		KieSession kieSession = kieContainer.newKieSession();
		
		//En este ejemplo agregamos un objeto de forma global a la sessi�n de drools en este caso el servicio PushSubService.java
		kieSession.setGlobal("publishTool", pushSubService);
		
		
		kieSession.insert(productPrice);
		kieSession.insert(new Message());
		
		kieSession.fireAllRules();
		
		/*
		 * * Libera todos los recursos de la sesi�n actual, configurando la sesi�n para la recolecci�n de basura.
		 * * Este m�todo debe ser llamado siempre despu�s de terminar de usar la sesi�n o el motor.
		 * * no liberar� la memoria utilizada por la sesi�n.
		 * */
		kieSession.dispose();
	}
	
}
