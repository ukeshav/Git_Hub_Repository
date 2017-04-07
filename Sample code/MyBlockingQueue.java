package Thread_Concurrent_package_Learnings;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class MyBlockingQueue<E> {
	private  LinkedList<E> list ;
	ReentrantLock lock = new ReentrantLock();
	Condition notFull = lock.newCondition();
	Condition notEmpty = lock.newCondition();	
	int capacity ; 

	MyBlockingQueue(int size){
		capacity = size;
		list = new LinkedList<E>();

	}
	public void add(E obj) throws InterruptedException{
		try{
			lock.lockInterruptibly();
			while(capacity == list.size()){
				System.out.println("+++++++++++++++++++++++++ Queue is full waiting++++++++++++++++");
				notFull.await();
			}
			list.add(obj);
			notEmpty.signalAll();

		}finally{
			lock.unlock();
		}

	}

	public E get() throws InterruptedException{
		try{
			lock.lockInterruptibly();			
			while(list.size() == 0){
				System.out.println("======================Queue is empty waiting===============");
				notEmpty.await();
			}

			E data = list.remove(0);
			notFull.signalAll();
			return data;

		}finally{
			lock.unlock();
		}
		
	}


	public static void main(String ... var){
		MyBlockingQueue<String > q = new MyBlockingQueue<String>(10);
		Producer p = new Producer(q);
		Consumer c = new Consumer(q);
		new Thread(c).start();
		new Thread(p).start();
		

	}
	
	static class Producer implements Runnable{
		MyBlockingQueue<String> queue;
		Producer(MyBlockingQueue<String> q){
			queue = q;
		}
		
		public void run(){
			int i = 0;
			String str = null;
			while(true){
				try {
					str = " Str -> "+ i++ ;
					queue.add( str);
					System.out.println("Producer Adding :  "+ str);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}


	static class Consumer implements Runnable{
		MyBlockingQueue<String> queue;
		Consumer(MyBlockingQueue<String> q){
			queue = q;
		}
		
		public void run(){
			int i = 0;
			while(true){
				try {
					System.out.println("Consumer got it : " +queue.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}

}


/**
 * Testing my blockingQueue with producer consurmer problem. 
 */





class Job extends Thread {
private Integer number = 0;
    public void run() {
    synchronized (this) {
        for (int i = 1; i < 1000000; i++) {
            number++;
        }
        try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        notify();
    }
    }
    public Integer getNumber() {
        return number;
    }
}
class Test12 {
    public static void main(String[] args) throws Exception {
        Job thread = new Job();
        thread.start();
        synchronized (thread) {

            thread.wait();
        }
        System.out.println(thread.getNumber());
    }
}


