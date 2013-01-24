package dsk.common.message;

public interface Message<R> {
	ChooseState showMessage(String message);

	R getValue();
}
