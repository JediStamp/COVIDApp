package midterm;

public class Demo implements Runnable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo obj = new Demo();
		Thread tobj = new Thread(obj);
		tobj.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread is in a running state");		
	}

}
