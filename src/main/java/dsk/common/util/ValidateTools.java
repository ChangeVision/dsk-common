package dsk.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ValidateTools {

    private static final Logger LOG = LoggerFactory.getLogger(ValidateTools.class);

    private ValidateTools() {
    }

    public static boolean validParagraph(final String text, final int col, final int row) {
        if (0 >= col || 0 >= row) {
            throw new IllegalArgumentException(String.format("col, rowは0以上を指定して下さい。col: %d, row: %d", col, row));
        }
        if (null == text) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        {
            String[] strs = text.split("\n");
            for (int i = 0; i < strs.length; ++i) {
                String str = strs[i];
                int index = 0;
                StringBuilder sb2 = new StringBuilder();
                if (0 != i) {
                    sb2.append("\n");
                }
                while (true) {
                    if (col >= str.length()) {
                        sb2.append(str);
                        break;
                    } else {
                        if (index + col >= str.length()) {
                            sb2.append(str.substring(index));
                            break;
                        } else {
                            sb2.append(str.substring(index, index + col));
                        }
                        sb2.append("\n");
                        index += col;
                    }
                }
                sb.append(sb2.toString());
            }
        }
        String str = sb.toString();
        if (str.replaceAll("\n", "").length() > col * row) {
            LOG.trace(String.format("%d > %d, col: %d, row: %d", str.length(), col * row, col, row));
            return false;
        }

        String[] strs = str.split("\n");
        return row >= strs.length;
    }
}
