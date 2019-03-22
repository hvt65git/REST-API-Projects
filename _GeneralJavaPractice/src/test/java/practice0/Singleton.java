package practice0;

interface Java8 {
	static void j18staticnoop() {}
	default void j18noop() {}
}

final class Singleton {
	private static final Singleton INSTANCE = new Singleton();

	private Singleton() {}

	public static Singleton getInstance() {
		return INSTANCE;
	}
}


