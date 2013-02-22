package dsk.common.exception;

/**
 * システムの事前条件違反で返すエラーはこのクラスを使うこと
 */
public class DskRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 2417030193778112930L;

	public DskRuntimeException() {
		super();
	}

	public DskRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DskRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public DskRuntimeException(String message) {
		super(message);
	}

	public DskRuntimeException(Throwable cause) {
		super(cause);
	}
}
