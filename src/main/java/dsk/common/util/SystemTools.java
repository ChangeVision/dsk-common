package dsk.common.util;

public final class SystemTools {

    private SystemTools() {
    }

    public static enum OS {

        WINDOWS,
        MACOSX,
        OTHER
    }

    public static OS getOs() {
        String osName = System.getProperty("os.name");
        if (osName.contains("Mac OS X")) {
            return OS.MACOSX;
        } else if (osName.contains("Windows")) {
            return OS.WINDOWS;
        }
        return OS.OTHER;
    }

    public static boolean isMacOsX() {
        return OS.MACOSX == SystemTools.getOs();
    }

    public static void main(String[] args) {
        System.out.println(getOs());
    }
}
