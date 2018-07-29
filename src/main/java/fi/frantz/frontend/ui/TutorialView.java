package fi.frantz.frontend.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import fi.frantz.service.MyService;

@Route(value = "", layout = MenuBar.class)
public class TutorialView extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4519843099295044241L;

	public TutorialView(@Autowired MyService myService) {
		setWidth("100%");
		Label text = new Label("Tutorials view: " + myService.getText());
		add(text);
	}
}