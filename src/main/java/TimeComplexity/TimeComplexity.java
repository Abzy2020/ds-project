package TimeComplexity;
import java.util.Stack;

public class TimeComplexity {
    // This is a simple class to measure the time complexity of a function
    private static Stack<Long> callTimes = new Stack<>();
    private static Stack<String> functionNames = new Stack<>();

    public static void startFunction(String functionName) {
        // This function should be called at the start of the function you want to measure
        callTimes.push(System.nanoTime());
        functionNames.push(functionName);
    }

    public static void endFunction() {
        // This function should be called at the end of the function you want to measure
        long endTime = System.nanoTime();
        long startTime = callTimes.pop();
        String functionName = functionNames.pop();
        long elapsedTime = endTime - startTime;
        // Print the time taken to run the function 
        System.out.println("Function " + functionName + " took " + elapsedTime + " nanoseconds");
    
    }


    
}
