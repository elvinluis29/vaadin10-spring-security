package fi.frantz.frontend.ui;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import ch.qos.logback.classic.Logger;

@Route("login")
public class LoginComponent extends VerticalLayout implements HasUrlParameter<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -579043829171007398L;

	Logger logger = (Logger) LoggerFactory.getLogger(LoginComponent.class);

	TextField usernameField;
	PasswordField passwordField;
	Checkbox remeberMeCheckbox;
	Button loginButton;

	Label logoutLabel;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	String paramText = "";

	public LoginComponent() {

		setAlignItems(Alignment.CENTER);

		logoutLabel = new Label();
		logoutLabel.getElement().getStyle().set("color", "red");
		logoutLabel.setVisible(false);
		add(logoutLabel);

		usernameField = new TextField();
		usernameField.setLabel("Username");

		passwordField = new PasswordField();
		passwordField.setLabel("Password");
		passwordField.addKeyPressListener(Key.ENTER, listener -> {
			logger.debug("Enter pressed");
			loginAction();
		});

		remeberMeCheckbox = new Checkbox();
		remeberMeCheckbox.setLabel("Remember me");

		loginButton = new Button("Login");
		loginButton.addClickListener(e -> {
			logger.debug("Login button pressed");
			loginAction();
		});
		add(usernameField, passwordField, remeberMeCheckbox, loginButton);
	}

	private void loginAction() {
		String username = usernameField.getValue();
		String password = passwordField.getValue();
		boolean rememberMe = remeberMeCheckbox.getValue();
		loginButton.setEnabled(false);
		if (login(username, password, rememberMe)) {
			loginButton.getUI().ifPresent(ui -> ui.navigate("secure"));
		} else {
			logoutLabel.setVisible(true);
			loginButton.setEnabled(true);
		}
	}

	private boolean login(String username, String password, boolean rememberMe) {

		try {
			Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			if (authenticate.isAuthenticated()) {
				Authentication fullyAuthenticated = new UsernamePasswordAuthenticationToken(username, password);

				SecurityContext context = SecurityContextHolder.getContext();

				context.setAuthentication(fullyAuthenticated);
				return true;
			}
		} catch (BadCredentialsException ex) {
			logoutLabel.setText("Incorrect username or password.");
			usernameField.focus();
			passwordField.setValue("");
		} catch (Exception ex) {
			Notification.show("An unexpected error occurred, please try again in a few minutes", 3000, Position.MIDDLE);
			logger.error("Unexpected error while logging in", ex);
		}
		return false;
	}

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		if (parameter != null && parameter.contentEquals("loggedout")) {
			logoutLabel.setVisible(true);
			logoutLabel.setText("You have been successfully logged out");
		}
	}
}