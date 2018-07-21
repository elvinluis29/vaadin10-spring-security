package fi.frantz.frontend.ui;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="icons", layout = MenuBar.class)
public class IconsView extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1776878619434720376L;

	public IconsView() {
		Label text = new Label("Icons view");
		add(text);
	}
}