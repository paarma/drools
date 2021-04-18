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
		 * Tener en cuneta que al ejecutar las reglas agrupadas por "agenda-group" tambi�n se ejecutan las que no tengan grupo definido.
		 * por ende si se quiere que solo se ejecuten las de un grupo particular entonces a TODAS las reglas se les deben definir diversos grupos.
		 * Con esto solo se ejecutar�n las del grupo indicado.
		 * */
		
		kieSession.getAgenda().getAgendaGroup("MessagesGroup").setFocus();
		
		kieSession.fireAllRules();
		
		/*
		 * * Libera todos los recursos de la sesi�n actual, configurando la sesi�n para la recolecci�n de basura.
		 * * Este m�todo debe ser llamado siempre despu�s de terminar de usar la sesi�n o el motor.
		 * * no liberar� la memoria utilizada por la sesi�n.
		 * */
		kieSession.dispose();
	}
	
}
