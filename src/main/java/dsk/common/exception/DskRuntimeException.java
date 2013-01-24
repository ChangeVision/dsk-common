package dsk.common.exception;

/**
 * システムの事前条件違反で返すエラーはこのクラスを使うこと<br>
 * ！！握りつぶさないこと！！
 */
public class DskRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 2417030193778112930L;

	protected long errorCode;

	public DskRuntimeException() {
		super();
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

	public DskRuntimeException(long errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public DskRuntimeException(String message, long errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public long getErrorCode() {
		return this.errorCode;
	}
}
