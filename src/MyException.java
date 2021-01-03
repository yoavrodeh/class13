@SuppressWarnings("serial")
public class MyException extends Exception {
	public enum Type {
		USER, ADMIN, SERVER, DATABASE;
	}

	private Type type;

	public MyException(Type type) {
		this.type = type;
	}
	public Type getType() {
		return type;
	}
}
