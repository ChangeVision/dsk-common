package dsk.common.exception;

/**
 * システムで判断して返すエラーはこのクラスを使うこと
 */
public class DskException extends Exception {
	private static final long serialVersionUID = -2842625796280025435L;

	public DskException() {
		super();
	}

	public DskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DskException(String message, Throwable cause) {
		super(message, cause);
	}

	public DskException(String message) {
		super(message);
	}

	public DskException(Throwable cause) {
		super(cause);
	}
}
