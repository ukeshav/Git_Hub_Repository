package Thread_Concurrent_package_Learnings;
import java.util.concurrent.atomic.AtomicInteger;


public class CountDownLatchPrg {

	public static void main(String ...var){
		System.out.println("Using count down latch");
		worker w = new worker();
		for (int i = 0;i <10 ; i++){
			new Thread(w).start();
		}
	}
}

class worker implements Runnable {

	MyCountDown latch = new MyCountDown(10);
	@Override
	public void run() {
		System.out.println("Starting the Thread" + Thread.currentThread().getName());
		try {
			latch.countDown();
			latch.await();
			System.out.println(" ===== Ending Thread the Thread" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class MyCountDown {
	AtomicInteger count;
	public MyCountDown(int n) {
              count = new AtomicInteger(n);
	}
	public synchronized void countDown(){
		
			count.getAndDecrement();
			if(count.get() ==0){
				notifyAll();
			}
		
	}
	
	public synchronized void await() throws InterruptedException{
		while (count.get() != 0){
			wait();			
		}
		
	}
	
}
