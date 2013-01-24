package dsk.common.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * 検証時エラーはこのクラスを投げること
 */
public class DskValidateException extends DskException {
	private static final long serialVersionUID = -6134274283311867886L;

	protected Set<?> violations;

	public DskValidateException() {
		super();
	}

	public <T> DskValidateException(Set<ConstraintViolation<T>> violations) {
		super();
		this.violations = violations;
	}

	public DskValidateException(long errorCode) {
		super(errorCode);
	}

	public DskValidateException(String message, long errorCode) {
		super(message, errorCode);
	}

	public DskValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	public DskValidateException(String message) {
		super(message);
	}

	public DskValidateException(Throwable cause) {
		super(cause);
	}

	/**
	 * 検証リストを取得する<br>
	 * 受け取った型へ自動的にキャストする。
	 * 
	 * @return violations
	 */
	@SuppressWarnings("unchecked")
	public <E> Set<ConstraintViolation<E>> getViolations() {
		return (Set<ConstraintViolation<E>>) violations;
	}
}
