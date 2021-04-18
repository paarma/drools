package com.ejemplo.drools.configuracion;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BPMConfigurations {

	private static final String[] drlFiles = {"rules/discountRules.drl"};
	
	@Bean
	public KieContainer kieContainer() {
		KieServices kieServices = KieServices.Factory.get();
		
		// Cargar reglas y definiciones de ecosistemas
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		
		for (String ruleFile : drlFiles) {
			kieFileSystem.write(ResourceFactory.newClassPathResource(ruleFile));
		}
		
		// Generar módulos y todas las estructuras internas
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();
		
		KieModule kieModule = kieBuilder.getKieModule();
		
		return kieServices.newKieContainer(kieModule.getReleaseId());
	}
	
}
