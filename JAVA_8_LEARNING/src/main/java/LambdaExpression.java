import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/**
 * 
 */

/**
 * @author k.upadhyaya
 *
 */
public class LambdaExpression {

	public static void main(String str[]){

		SingleMethodInterface obj = (a, b) -> {System.out.println("I am in the method.." + a + b);
		System.out.println("Multi lines are supported.. Using curley braces");};
		obj.print(2,6);

		new LambdaExpression().scopeOfLambdaVrsMethodImplementation();
		subSingleMethodInterface  intface = (a, b)  -> System.out.println(a+b);
		((SingleMethodInterface)intface).print1();
		
		
		
		
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