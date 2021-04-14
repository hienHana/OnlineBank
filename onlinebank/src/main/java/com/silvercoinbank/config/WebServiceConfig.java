package com.silvercoinbank.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.silvercoinbank.ws.AccountWebService;
import com.silvercoinbank.ws.BranchWebService;

@Configuration
public class WebServiceConfig {
	@Autowired
	private Bus bus;
	
	@Autowired
	BranchWebService branchWebService;
	
	@Autowired
	AccountWebService accountWebService;
	
	@Bean
	public SpringBus cxf() {
		return new SpringBus();
	}
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new CXFServlet(), "/services/*");
	}
	
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, branchWebService);
		endpoint.publish("/branchWebService");
		endpoint = new EndpointImpl(bus, accountWebService);
		endpoint.publish("/accountWebService");
		return endpoint;
	}
}
