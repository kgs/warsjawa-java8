package pl.warsjawa.java8.defmethods;


public interface Job extends Lifecycle {

	/**
	 * Do not TOUCH!
	 */
	default int start() {
		return 2;
	}

}
