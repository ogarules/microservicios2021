package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Primary
	@Bean
	public SwaggerResourcesProvider swaggerResourcesProvider(){
		ArrayList<String> apis = new ArrayList<>();
		apis.add("organization");
		apis.add("department");
		apis.add("employee");

		return () -> {
			List<SwaggerResource> resources = new ArrayList<>();
			apis.forEach(route -> {
				resources.add(createResource(route, "2.0"));
			});

			return resources;
		};
	}

	private SwaggerResource createResource(String route, String version){
		SwaggerResource resource = new SwaggerResource();
		resource.setName(route);
		resource.setLocation("/" + route + "/v2/api-docs");
		resource.setSwaggerVersion(version);

		return resource;
	}
}
