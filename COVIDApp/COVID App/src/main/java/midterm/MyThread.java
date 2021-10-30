package midterm;

public class MyThread extends Thread {
MyThread(){
	
}

MyThread(Runnable r){
	super(r);
}

public void run() {
	System.out.print("Inside Thread ");
}
}
