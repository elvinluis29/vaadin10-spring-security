package fi.frantz.vaadin1.application;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.vaadin.flow.spring.SpringServlet;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		registerConfiguration(context);
		servletContext.addListener(new ContextLoaderListener(context));

		ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new SpringServlet(context));
		registration.setLoadOnStartup(1);
		registration.addMapping("/*");

		servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
				.addMappingForUrlPatterns(null, false, "/*");
	}

	private void registerConfiguration(AnnotationConfigWebApplicationContext context) {
		// register your configuration classes here
		context.setConfigLocation("fi.frantz.vaadin1.application.XmlConfig, fi.frantz.vaadin1.application.XmlConfigSecurity");
	}

}
