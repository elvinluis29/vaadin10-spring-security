package fi.frantz.frontend.ui;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MenuBar.class)
public class TutorialView extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4519843099295044241L;

	public TutorialView() {
		Label text = new Label("Tutorials view");
		add(text);
	}
}