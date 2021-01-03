public class RunnableExample {
	static int x = 0;

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			x += 5;
			System.out.println("Thread " + x);
		});
		t.start();
		x += 5;
		System.out.println("Main " + x);
	}
}
