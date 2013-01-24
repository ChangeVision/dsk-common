package dsk.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dsk.common.exception.DskRuntimeException;

public final class IoTools {
	private static final Logger LOG = LoggerFactory.getLogger(IoTools.class);

	private IoTools() {
	}

	public static void close(InputStream is) {
		try {
			if (null != is) {
				is.close();
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new DskRuntimeException(e);
		}
	}

	public static void close(OutputStream os) {
		try {
			if (null != os) {
				os.close();
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new DskRuntimeException(e);
		}
	}

	public static void close(Reader r) {
		try {
			if (null != r) {
				r.close();
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new DskRuntimeException(e);
		}
	}

	public static void close(Writer w) {
		try {
			if (null != w) {
				w.close();
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new DskRuntimeException(e);
		}
	}

	/**
	 * 指定したディレクトリを作成する
	 * 
	 * @param dirPath
	 *            作成するディレクトリのパス
	 * @return dirPathの最後が'/'で終わっている場合は、'/'を削除したものが返る
	 */
	public static String createDirectory(String dirPath) {
		File f = new File(dirPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		if (dirPath != null && !dirPath.isEmpty() && dirPath.charAt(dirPath.length() - 1) == '/') {
			return dirPath.substring(0, dirPath.length() - 1);
		}
		return dirPath;
	}
}
