package fi.frantz.vaadin1.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = "classpath:applicationContext-frontend.xml")
class XmlConfig {}