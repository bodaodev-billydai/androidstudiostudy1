package spark.launcher;

public interface Dockable {
	int dock(String root, String opt);

	int preDdock(String root, String opt);
}
