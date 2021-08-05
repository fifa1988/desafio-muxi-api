package br.com.muxi.desafio;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication(scanBasePackages = "br.com.muxi.desafio")
public class DesafioMuxiApiApplication extends SpringBootServletInitializer implements ApplicationContextAware {
	
//	private static ApplicationContext CONTEXT;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DesafioMuxiApiApplication.class);
	}

	@PostConstruct
	public void init() { 
		TimeZone.setDefault(TimeZone.getTimeZone("America/Recife")); 
	}

	public static void main(String[] args) {
		SpringApplication.run(DesafioMuxiApiApplication.class, args);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		CONTEXT = applicationContext;
	}

}
