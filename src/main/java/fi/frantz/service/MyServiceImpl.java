package fi.frantz.service;

import org.springframework.stereotype.Component;

@Component
public class MyServiceImpl implements MyService {

	@Override
	public String getText() {
		return "Hello from Spring service!";
	}

}
