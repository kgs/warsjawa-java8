package pl.warsjawa.java8.defmethods;

public interface Engine extends Lifecycle {

	/**
	 * Do not TOUCH!
	 */
	default int start() {
		return 3;
	}

}

