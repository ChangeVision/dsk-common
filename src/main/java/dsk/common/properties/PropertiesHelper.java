package dsk.common.properties;

public interface PropertiesHelper {
	void load();

	void save();

	// TODO primitiveもほしい
	String get(String key);

	String get(String key, String defaultValue);

	void set(String key, String value);
}
