package TimeComplexity;
import java.util.Stack;

public class TimeComplexity {
    private static Stack<Long> callTimes = new Stack<>();
    private static Stack<String> functionNames = new Stack<>();

    public static void startFunction(String functionName) {
        callTimes.push(System.nanoTime());
        functionNames.push(functionName);
    }

    public static void endFunction() {
        long endTime = System.nanoTime();
        long startTime = callTimes.pop();
        String functionName = functionNames.pop();
        long elapsedTime = endTime - startTime;

        System.out.println("Function " + functionName + " took " + elapsedTime + " nanoseconds");
    }


    
}
