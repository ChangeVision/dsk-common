package dsk.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class R {
	private static final Logger LOG = LoggerFactory.getLogger(R.class);
	private static final String MESSAGE_RESOURCES = "message";

	private static Map<String, String> cacheMap = new HashMap<String, String>();

	private R() {
	}

	public static String m(String key) {
		return m(MESSAGE_RESOURCES, key);
	}

	public static String m(String id, String key) {
		String theKey = new StringBuilder(id).append(key).toString();
		if (cacheMap.containsKey(theKey)) {
			return cacheMap.get(theKey);
		}
		String value = null;
		try {
			value = ResourceBundle.getBundle(id).getString(key);
		} catch (MissingResourceException e) {
			LOG.warn(e.getMessage());
			value = key;
		}
		cacheMap.put(theKey, value);
		return value;
	}
}
