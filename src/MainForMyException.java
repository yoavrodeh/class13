public class MainForMyException {
	public static void main(String[] args) {
		try {
			throw new MyException(MyException.Type.SERVER);
		} catch (MyException e) {
			System.out.println(e.getType());
			// SERVER
		}
	}
}
