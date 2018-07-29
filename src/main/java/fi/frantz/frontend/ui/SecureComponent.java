package fi.frantz.frontend.ui;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;

@RoutePrefix("secure")
public class SecureComponent extends VerticalLayout implements RouterLayout {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8067004378729700160L;

	public SecureComponent(){
		setSizeFull();
		 Label loginLabel = new Label("Secure application");
		 loginLabel.getElement().getStyle().set("font-weight", "bold");
		 loginLabel.getElement().getStyle().set("font-size", "150%"); add(loginLabel);
		 add(loginLabel);
    }
	
}