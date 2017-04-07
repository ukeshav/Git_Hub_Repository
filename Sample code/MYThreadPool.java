package Thread_Concurrent_package_Learnings;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;


public class MYThreadPool {

	int count;
	ArrayList<MyThread> listOfthreads;
	ArrayBlockingQueue<Runnable> queue;
	
	public MYThreadPool(int count){
		this.count = count;		
		queue = new ArrayBlockingQueue<Runnable>(Short.MAX_VALUE);		
	}
	
	public void execute(Runnable runnable){		
		checkInitializatioin();
		queue.add(runnable);		
		
	}
	
	
	private void checkInitializatioin(){
		if(listOfthreads == null){
			synchronized (this) {
				listOfthreads =  new ArrayList<MyThread>(count);
				for(int i = 0; i < count ; i++){
					MyThread thread = new MyThread(queue);
					listOfthreads.add(thread);
					thread.start();
					
				}
				
			}
		}
		
	}
	
	class MyThread extends Thread{
		ArrayBlockingQueue<Runnable> queue;
		
		public MyThread(ArrayBlockingQueue<Runnable> queue){
			this.queue = queue;
		}
		@Override
		public void run(){
			while( isInterrupted()){
				Runnable runnable;
				try {
					runnable = queue.take();
					runnable.run();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	
	
	
	// Testing MyThead Pool. 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MYThreadPool pool = new MYThreadPool(3);
		Runnable run = new Runnable(){
			public void run(){
				System.out.println("Hi i am here" + Thread.currentThread().getName());
				
			}
		};
		
		for(int i = 0 ; i < 50 ; i++){
			pool.execute(run);
		}

	}

}




