package com.ejemplo.drools.servicio;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.drools.modelo.Message;
import com.ejemplo.drools.modelo.ProductPrice;

@Service
public class EjecutarReglasPorAgrupacionAgendaService {

	@Autowired
	private KieContainer kieContainer;
	
	public void executeRules(ProductPrice productPrice, Message mensaje) {
		KieSession kieSession = kieContainer.newKieSession();
		
		kieSession.insert(productPrice);
		kieSession.insert(mensaje);
		
		//Se establecen las reglas a disparar. Solo las que tengan la propiedad agenda-group : "MessagesGroup"
		
		/*
		 * Tener en cuneta que al ejecutar las reglas agrupadas por "agenda-group" también se ejecutan las que no tengan grupo definido.
		 * por ende si se quiere que solo se ejecuten las de un grupo particular entonces a TODAS las reglas se les deben definir diversos grupos.
		 * Con esto solo se ejecutarán las del grupo indicado.
		 * */
		
		kieSession.getAgenda().getAgendaGroup("MessagesGroup").setFocus();
		
		kieSession.fireAllRules();
		
		/*
		 * * Libera todos los recursos de la sesión actual, configurando la sesión para la recolección de basura.
		 * * Este método debe ser llamado siempre después de terminar de usar la sesión o el motor.
		 * * no liberará la memoria utilizada por la sesión.
		 * */
		kieSession.dispose();
	}
	
}
