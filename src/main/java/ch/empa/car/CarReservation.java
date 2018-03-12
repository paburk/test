package ch.empa.car;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class CarReservation implements Serializable, Cloneable {

	private static final long serialVersionUID = 1;

	private LocalDateTime takeOverDate;

	public LocalDateTime getTakeOverDate() {
		return takeOverDate;
	}

	public void setTakeOverDate(LocalDateTime takeOverDate) {
		this.takeOverDate = takeOverDate;
	}

	public void reset() {
		for (Field f : this.getClass().getDeclaredFields()) {
			try {
				f.set(this, null);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}


}
