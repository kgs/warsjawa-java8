package pl.warsjawa.java8.defmethods;

public class RuleEngine implements Job, Engine {

    @Override
	public int start() {
		return Job.super.start();
	}
}
