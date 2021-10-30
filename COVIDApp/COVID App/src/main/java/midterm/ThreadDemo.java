package midterm;

//public class ThreadDemo {
//public static void main(String[] args) {
//	new MyThread().start();
//	new MyThread(new RunnableDemo()).start();
//}
//}

public class ThreadDemo extends Thread{
	public static void main(String[] args) {
		ThreadDemo t = new ThreadDemo();
		t.start();
		System.out.print("one. ");;
		t.start();
		System.out.print("two. ");;
	}
	
	public void run() {
		System.out.print("Thread ");
			}
}