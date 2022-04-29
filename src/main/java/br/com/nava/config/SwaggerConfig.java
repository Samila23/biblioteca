package br.com.nava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration														//Claase de configuração
public class SwaggerConfig {
	@Value("${app.name}")												//Nome da aplicação
	private String appName;
	
	@Value("{app.description}")
	private String appDescription;
	
	@Value("{app.Version}")
	private String appVersion;
	
	//Metodo padrão
	public OpenAPI openApi() {
		return new OpenAPI().info(new Info().title(appDescription).version(appVersion));
	}
	
	
}
