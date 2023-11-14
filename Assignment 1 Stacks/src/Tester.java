// import scanner
import java.util.Scanner;

/**
 * class used to run automated tests on stack and also to test the Rpn calculator.
 * 
 * @author Jamie Fergus 190018054
 */
public class Tester
{
	// declare fields
    private Stack testStack;
    private RPNCalculator testRPN;
    
    /**
     * Create an instance of your test class and then run various test methods.
     * @param args
     */
    public static void main(String args[])
    {
        Tester test = new Tester();
        test.init();
        test.process();
    }

    /**
     * test initialisation code
     */
    private void init()
    {
       testStack = new Stack();
       testRPN = new RPNCalculator();
    }

    
    /**
     * Process our tests
     */
    private void process()
    {
    	// automated tests:
    	
    	//stackTests();
    	rpnTests();
    }
    
    /**
     * run RPN to test
     */
    private void rpnTests()
    {
    	testRPN.evaluatePostfix();
    }
    
    /**
     * Run automatic stack tests
     */
    private void stackTests()
    {
    	// Add some nodes and print out the list:
    	
    	testStack.initialiseStack();
    	
    	//testStack.push(42);
    	//testStack.printStack();
    	
    	//testStack.push(17);
    	//testStack.push(50);
    	//testStack.push(-3);
    	//testStack.printStack();
    	
    	//testStack.push(67);
    	//testStack.push(32);
    	//testStack.pop();
    	//testStack.printStack();
    	
    	//System.out.println("Is stack empty : " + testStack.isStackEmpty());
    	//testStack.printStack();
    	
    	//testStack.push(1);
    	//testStack.push(2);
    	//testStack.push(3);
    	//System.out.println("Is stack empty : " + testStack.isStackEmpty());
    	//testStack.printStack();
    	
    	testStack.printStack();
    }
}
