package dsk.common.util;

public final class DesktopTools {
	private DesktopTools() {
	}

	public static String getHomeDirectoryPath() {
		return System.getProperty("user.home");
	}
}
