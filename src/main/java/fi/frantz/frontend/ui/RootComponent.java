package fi.frantz.frontend.ui;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class RootComponent extends VerticalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1802529070912413117L;

	public RootComponent(){
		Label greeting = new Label("Hello user!");
		add (greeting);
		add(new RouterLink("Go to login page", LoginComponent.class));
    }
}
