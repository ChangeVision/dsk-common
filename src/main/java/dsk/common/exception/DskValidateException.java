package dsk.common.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * 検証時エラーはこのクラスを投げること
 */
public class DskValidateException extends DskException {
	private static final long serialVersionUID = -6134274283311867886L;

	protected Set<?> violations;

<<<<<<< HEAD
=======
	public DskValidateException() {
		super(null, null, false, false);
	}

>>>>>>> commit.
	public <T> DskValidateException(Set<ConstraintViolation<T>> violations) {
		super(null, null, false, false);
		this.violations = violations;
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
