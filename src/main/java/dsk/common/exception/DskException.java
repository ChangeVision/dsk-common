package dsk.common.exception;

/**
 * システムで判断して返すエラーはこのクラスを使うこと<br>
 * ！！握りつぶさないこと！！
 */
public class DskException extends Exception {
	private static final long serialVersionUID = -2842625796280025435L;

	protected long errorCode;

	public DskException() {
		super();
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

	public DskException(long errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public DskException(String message, long errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public long getErrorCode() {
		return this.errorCode;
	}
}
