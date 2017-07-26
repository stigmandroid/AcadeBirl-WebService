package br.com.vpgdev.acadebirlwebservice.rest;


import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.jettison.JettisonFeature;


public class MyApplication extends Application {
	/*@Override
	public Set<Object> getSingletons() {
		
		Set<Object> singletons = new HashSet<Object>();
		
		// Driver do Jettison para gerar o JSON
		singletons.add(new JettisonFeature());
		return singletons;
	}*/	
	@Override
	public Map<String, Object> getProperties() {
		
		Map<String, Object> properties = new HashMap<String, Object>();
		
		// Configura o pacote para fazer scan das classes com anotações REST
		properties.put("jersey.config.server.provider.packages", "br.com.vpgdev.acadebirlwebservice");
		return properties;
		
	}
	
}
