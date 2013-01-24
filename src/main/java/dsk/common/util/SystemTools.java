package dsk.common.util;

public final class SystemTools {
	private SystemTools() {
	}

	public static enum OS {
		WINDOWS, MACOSX, OTHER
	}

	public static OS getOs() {
		String osName = System.getProperty("os.name");
		if (osName.indexOf("Mac OS X") >= 0) {
			return OS.MACOSX;
		} else if (osName.indexOf("Windows") >= 0) {
			return OS.WINDOWS;
		}
		return OS.OTHER;
	}

	public static boolean isMacOsX() {
		if (OS.MACOSX == SystemTools.getOs()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(getOs());
	}
}
