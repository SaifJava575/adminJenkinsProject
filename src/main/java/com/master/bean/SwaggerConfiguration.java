package com.master.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
			ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier,
			EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties,
			WebEndpointProperties webEndpointProperties, Environment environment) {
		List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
		Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
		allEndpoints.addAll(webEndpoints);
		allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
		allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
		String basePath = webEndpointProperties.getBasePath();
		EndpointMapping endpointMapping = new EndpointMapping(basePath);
		boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment,
				basePath);
		return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes,
				corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath),
				shouldRegisterLinksMapping, null);
	}

	private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment,
			String basePath) {
		return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath)
				|| ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
	}

	/*
	 * private ApiInfo apiInfo() { return new ApiInfo("Master Service",
	 * "API doc for Master Service", "0.0.1.SNAPSHOT", null, new
	 * Contact("Arvind Kumar", null, "logicsoft137@gmail.com"), null, null,
	 * Collections.emptyList()); }
	 */

//	Tag tags = new Tag("MasterSubModule", "master controller subModule api");
//	Tag tag1 = new Tag("MasterModule", "master controller MasterModule api");

	
	@Bean
	public Docket api() {		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.select().apis(RequestHandlerSelectors.basePackage("com.master.controller"))
				.paths(PathSelectors.any())	
				.build()
				.useDefaultResponseMessages(false)
				   .globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages() )
				   .globalResponseMessage(RequestMethod.POST, getCustomizedResponseMessages())
				   .globalResponseMessage(RequestMethod.PUT, getCustomizedResponseMessages())
				   .globalResponseMessage(RequestMethod.DELETE, getCustomizedResponseMessages());
//		        .tags(tags)
//		        .tags(tag1);
	}

	private ApiInfo getInfo() {
		return new ApiInfoBuilder().title("Master Admin Service").description("API doc for master Service")
				.version("0.0.1.SNAPSHOT").termsOfServiceUrl("Term of service")
				.contact(new Contact("MASTER ADMIN", null, "sksaifuddin575@gmail.com")).build();
	}

	private List<ResponseMessage> getCustomizedResponseMessages() {
		List<ResponseMessage> responseMessages = new ArrayList<>();
		responseMessages.add(new ResponseMessageBuilder().code(200).message("Sucess!!").build());
		responseMessages.add(new ResponseMessageBuilder().code(304)
				.message("The resource was available and not modified !!").build());
		responseMessages.add(new ResponseMessageBuilder().code(201)
				.message("The request succeeded and created a newresource on the server.!!").build());
		responseMessages.add(new ResponseMessageBuilder().code(417).message("Exception occurs.!!")
				.responseModel(new ModelRef("Error")).build());
		responseMessages.add(new ResponseMessageBuilder().code(400).message("Bad Request !!").build());
		responseMessages.add(new ResponseMessageBuilder().code(500).message("Internal Server error!!")
				.responseModel(new ModelRef("Error")).build());
		responseMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized request !!").build());
		return responseMessages;
	}

}
