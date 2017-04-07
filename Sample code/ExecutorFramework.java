package Thread_Concurrent_package_Learnings;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ExecutorFramework {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ScheduledThreadPoolExecutor ste = new ScheduledThreadPoolExecutor(10);
		
		ste.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("sdfsfsf");
				
			}
		});
		
		
		// You can schedule this for configurable  time. 
		ste.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				//System.out.println("sdfsfsf11111111111");
				
			}
		}, 10, 3000, TimeUnit.MILLISECONDS);

	ExecutorFramework();
		
//		completionService();
		
		futureTaskSample();
		
	}
	
	/**
	 * Executor Framework usage and ThreadFactory used which helps to create threads in a thread pool and 
	 * provide user defined way of creating Threads. 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * 
	 */
	public static void ExecutorFramework() throws InterruptedException, ExecutionException{
		
		ExecutorService ex = Executors.newFixedThreadPool(10, new ThreadFactory() {
			
			int i = 0;
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				t.setName("Keshav" + i++);
				return t;
			}
		});
		
		for(int i = 0 ; i< 20 ; i++){
			
			//execute takes runnable. 
		ex.execute(new Runnable() {
			
			@Override
			public void run() {
					System.out.println("exectue din executor framework" + Thread.currentThread().getName()) ;
				
			}
		});
		}
		
		//submit takes callabe and return Future as return . 
		
		Future ftr = ex.submit(new Callable() {
			public Object  call() throws Exception{
				 return "sdfsfsfd";
			 }
		});
		
		System.out.println(ftr.get());
		
	}
	
	/**
	 * Completions service is a queue based where you can keep on adding task . 
	 * and later on reterived by using a loop  and completion service return a futuere 
	 * then you can call get on the future to get the result . 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void completionService() throws InterruptedException, ExecutionException{
		
		CompletionService s = new ExecutorCompletionService(Executors.newFixedThreadPool(10));
		
		for(int i = 0 ; i< 20 ; i++){
			s.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					return "Hiiiiiiiiiiiii";
				}
				
			});
		}
		
		for(int i = 0 ; i < 30 ; i ++){
			Future<String> f = s.take();
			System.out.println( i +" : " + f.get());
		}
		
		
	}
	
	
	/**
	 * Future task takes runnable , callable 
	 * can be executed in Thread 
	 * 
	 *  With FutureTask later on you can return result of by using get function.  
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	
	public static void futureTaskSample() throws InterruptedException, ExecutionException {
		
		FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
			return "sdfsfsfs";
			}
		});
		
	
	
		new Thread(task).start();
		Executors.newFixedThreadPool(10).submit(task);
		
		System.out.println(task.get());
		
		
		String result = "-----------";
		FutureTask<String> taskWith_Runnavble = new FutureTask<String>(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("");
				
			}
		}, result);
		
		new Thread(taskWith_Runnavble).start();
		
		System.out.println(taskWith_Runnavble.get());
	}

}
