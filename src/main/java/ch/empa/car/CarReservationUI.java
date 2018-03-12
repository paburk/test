package ch.empa.car;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;

/**
 * This UI is the application entry point. A UI may either represent a browser window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be overridden to add component to the user interface and initialize
 * non-component functionality.
 */
public class CarReservationUI extends UI {
	private static Logger logger = Logger.getLogger(CarReservationUI.class);
	private static final long serialVersionUID = 1L;


	private CarReservationForm carReservationForm;

	@VaadinServletConfiguration(ui = CarReservationUI.class, productionMode = true)
	public static class CarReservationUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		this.refresh(vaadinRequest);
	}


	@Override
	protected void refresh(VaadinRequest request) {

		final VerticalLayout layout = new VerticalLayout();

		this.carReservationForm = new CarReservationForm(this);
		layout.addComponent(carReservationForm);
		layout.setComponentAlignment(carReservationForm, Alignment.TOP_CENTER);

		setContent(layout);
		super.refresh(request);
	}

}
