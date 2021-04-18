package com.ejemplo.drools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ejemplo.drools.modelo.Message;
import com.ejemplo.drools.modelo.ProductPrice;
import com.ejemplo.drools.servicio.EjecutarReglasPorAgrupacionAgendaService;
import com.ejemplo.drools.servicio.PriceCalculatorService;

@SpringBootTest
public class EjecutarReglasDroolsTest {

	@Autowired
	private PriceCalculatorService priceCalculatorService;
	
	@Autowired
	private EjecutarReglasPorAgrupacionAgendaService agrupacionService;
		
	@Test
	public void executeCalculations() {
		
		//Se crea el "echo" (objeto)
		ProductPrice productPrice = new ProductPrice(5);
		
		//Se ejecutan las reglas de drools (Se llama al motor de reglas)
		priceCalculatorService.executeRules(productPrice);
		
		//Se verifica el resultado luego de pasar por las reglas
		System.out.println("luego de pasar por las reglas de drools: "+productPrice);
	}
	
	@Test
	public void executeAgrupacionReglas() {
		
		//Echos (En este ejemplo se envían dos objetos. Se pueden enviar la cantidad de objetos necesarios a drools)
		ProductPrice productPrice = new ProductPrice(5);
		Message message = new Message();
		
		agrupacionService.executeRules(productPrice, message);
		
		System.out.println("Segundo ejemplo con agrupación: "+message.getMessage());
	}
	
}
