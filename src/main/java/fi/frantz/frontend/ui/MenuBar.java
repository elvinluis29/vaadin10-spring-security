package fi.frantz.frontend.ui;

import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

import ch.qos.logback.classic.Logger;

@ParentLayout(SecureComponent.class)
public class MenuBar extends HorizontalLayout implements RouterLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6717086211943609930L;

	VerticalLayout menu;
	
	Logger logger = (Logger) LoggerFactory.getLogger(MenuBar.class);
	
    public MenuBar() {
    	menu = new VerticalLayout();
    	menu.getElement().getStyle().set("background-color", "#CFCFCF");
    	menu.setSpacing(false);
    	add(menu);
    	
        addMenuElement(TutorialView.class, "Tutorial", VaadinIcon.GIFT);
        addMenuElement(IconsView.class, "Icons", VaadinIcon.CAR);
        
    }
    private void addMenuElement(Class<? extends Component> navigationTarget,
            String name, VaadinIcon icon) {
    	Button button = new Button(name, new Icon(icon));
    	button.addClickListener( e-> {
    	     button.getUI().ifPresent(ui -> ui.navigate(navigationTarget));
    	});
        button.getElement().getStyle().set("color", "black");
        button.getElement().getStyle().set("background-color", "#CFCFCF");
    	menu.add(button);
    }
    
    protected void onAttach(AttachEvent attachEvent) {
        UI ui = getUI().get();
        Button button = new Button("Logout", event -> {
            // Redirect this page immediately
            ui.getPage().executeJavaScript(
                        "window.location.href='/vaadin10-spring-security/logout'");

            // Close the session
            ui.getSession().close();
        });

        button.setIcon(new Icon(VaadinIcon.EXIT));
        button.getElement().getStyle().set("color", "black");
        button.getElement().getStyle().set("background-color", "#CFCFCF");
        menu.add(button);

        // Notice quickly if other UIs are closed
        ui.setPollInterval(3000);
    } 

}