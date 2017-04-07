package Thread_Concurrent_package_Learnings;

public class CyclicBarrierMy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Using count down latch");
		worker1 w = new worker1();
		for (int i = 0;i <10 ; i++){
			new Thread(w).start();
		}

	}



}

class worker1 implements Runnable {

	MyCyclicBarrier cyclicbarrier = new MyCyclicBarrier(10);
	@Override
	public void run() {
		System.out.println("Starting the Thread" + Thread.currentThread().getName());
		try {	
			cyclicbarrier.await();
			System.out.println(" ===== Ending Thread the Thread" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}


class MyCyclicBarrier{
	private volatile int i ;
	public MyCyclicBarrier(int n){
		i = n;
	}
	
	public synchronized void await() throws InterruptedException{
		i--;
		while(i!=0){
			wait();
		}
		notifyAll();
	}
	
}