package ch.empa.car;

import com.vaadin.data.Binder;
import com.vaadin.ui.Notification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
class CarReservationForm extends CarReservationFormDesign {

	private CarReservation carReservation;
	private Binder<CarReservation> binder = new Binder<>(CarReservation.class);
	private DateTimeFormatter timeFormatter;
	private CarReservationUI carReservationUI;

	public CarReservationForm(CarReservationUI carReservationUI) {
		initializeForm(carReservationUI);
		makeBindings();

		// Set the listeners for the dates
		setTakeOverDateListeners();

		// Add button listeners
		resetButton.addClickListener(e -> this.reset());

	}

	private void makeBindings() {
		// Set the bean to shall be updated bidirectional
		binder.setBean(this.carReservation);

		// Automatic binding for all other fields
		binder.bindInstanceFields(this);

	}

	private boolean isAlreadyTruncated(LocalDateTime localDateTime) {
		if (localDateTime.isEqual(truncate(localDateTime))) {
			return true;
		}
		return false;
	}

	private LocalDateTime truncate(LocalDateTime localDateTime) {
		return localDateTime.truncatedTo(MyChronoUnit.THIRTY_MINUTES);
	}

	private void setTakeOverDateListeners() {
		// Has a listener which caps the time to 30 minutes
		takeOverDate.setShowISOWeekNumbers(true);
		takeOverDate.setRangeStart(LocalDateTime.now());
		takeOverDate.setDefaultValue(LocalDateTime.now().plusDays(1).withHour(9).withMinute(0));
		takeOverDate.setDateOutOfRangeMessage("error.date_out_of_range");
		takeOverDate.setParseErrorMessage("error.date_invalid_format");
		takeOverDate.setDateFormat(DateTimeFormat.DATE_TIME_FORMAT);

		takeOverDate.addValueChangeListener(event -> {
			if (!takeOverDate.isEmpty()) {
				if (!isAlreadyTruncated(takeOverDate.getValue())) {
					takeOverDate.setValue(truncate(takeOverDate.getValue()));
					Notification.show("warn.time_truncated" + ": " + takeOverDate.getValue().format(timeFormatter),
							Notification.Type.WARNING_MESSAGE);
				}
			}
		});

	}

	private void initializeForm(CarReservationUI carReservationUI) {
		this.carReservationUI = carReservationUI;
		this.timeFormatter = DateTimeFormatter.ofPattern(DateTimeFormat.TIME_FORMAT);
		this.carReservation = new CarReservation();
	}

	private void reset() {
		this.carReservation.reset();
		binder.readBean(this.carReservation);
	}

}
