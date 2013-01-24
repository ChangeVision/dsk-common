package dsk.common.exception;

/**
 * システムで判断して返す握りつぶしてよいエラーはこのクラスを使うこと
 */
public class DskWarningException extends DskException {
	private static final long serialVersionUID = 1751654498576565692L;

	public DskWarningException() {
		super();
	}

	public DskWarningException(long errorCode) {
		super(errorCode);
	}

	public DskWarningException(String message, long errorCode) {
		super(message, errorCode);
	}

	public DskWarningException(String message, Throwable cause) {
		super(message, cause);
	}

	public DskWarningException(String message) {
		super(message);
	}

	public DskWarningException(Throwable cause) {
		super(cause);
	}
}
