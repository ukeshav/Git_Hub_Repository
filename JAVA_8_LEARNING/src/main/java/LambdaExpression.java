import java.util.Arrays;
import java.util.stream.Stream;

import com.sun.istack.internal.NotNull;

/**
 * 
 */

/**
 * @author k.upadhyaya
 *
 */
public class LambdaExpression {

	@NotNull public static void main(String str[]){

		SingleMethodInterface obj = (a, b) -> {System.out.println("I am in the method.." + a + b);
		System.out.println("Multi lines are supported.. Using curley braces");};
		obj.print(2,6);

		new LambdaExpression().scopeOfLambdaVrsMethodImplementation();
		subSingleMethodInterface  intface = (a, b)  -> System.out.println(a+b);
		((SingleMethodInterface)intface).print1();
		
//		List<E> abc = new ArrayList<E>();
//		
//		Optional a ;
//		Stream<String> s;
//		
		//s.filter(s-> s.contains("sdfsdf")).forEach(action). ;
		
		Arrays.stream(new int[] {1, 2, 3})
	    .map(n -> 2)
	    .average()
	    .ifPresent(System.out::println); 

		Stream.of("a1", "a2", "a3")
	    .map(s -> s.substring(0))
	    .mapToInt(Integer::parseInt)
	    .max()
	    .ifPresent(System.out::println);  // 3

		
		
	}

	public void scopeOfLambdaVrsMethodImplementation(){
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				System.out.println(this); //This will print the Runnable instance. 

			}
		};

		r1.run();


		Runnable r2_lambda = () -> System.out.println(this); // This will print the LambdaExpression class instance. 
		r2_lambda.run();
	}


	@Override
	public String toString(){
		return "Inlambda class..";
	}
}


interface SingleMethodInterface{

	public void print(int a, int b);

	default public void print(){
		System.out.println("Inside default...");
	}

	//You can have a as many as default method you need.. 
	//So the if the implementing class is not implementing all the methods then default methods will be used. 
	default public void print1(){
		System.out.println("Inside default...");
	}


}


interface subSingleMethodInterface extends SingleMethodInterface{
	
	//THe default method can be overridden 
	default public void print1(){
		System.out.println("Inside child default...");
	}
}