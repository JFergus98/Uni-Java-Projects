/**
 * used to test the lottery and MySet class
 * 
 * @author Jamie Fergus 190018054 
 */
public class Tester {
	private MySet test1, test2;
	private Lottery testLottery;
									
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tester test = new Tester();
		test.init();
        test.process();
	}
	
	/**
	 * process tests
	 */
	private void process() {
		//testMySet();
		
		testLottery();
	}
	
	private void testLottery() {
		testLottery.playTheLottery();
	}
	
	/**
	 * perform tests on MySet class
	 */
	private void testMySet() {
		test1.printSet();
		test2.printSet();
		
		System.out.println("");
		
		System.out.println(test1.isSetEmpty());
		System.out.println(test2.isSetEmpty());
		
		System.out.println("");
		
		System.out.println(test1.getCardinality());
		System.out.println(test2.getCardinality());
		
		System.out.println("");
		
		System.out.println("");
		
		System.out.println(test1.addToSet(1));
		System.out.println(test1.addToSet(2));
		System.out.println(test1.addToSet(3));
		System.out.println(test1.addToSet(19));
		System.out.println(test1.addToSet(19));
		System.out.println(test1.addToSet(12));
		System.out.println(test1.addToSet(63));
		
		System.out.println("");
		
		System.out.println(test2.addToSet(1));
		System.out.println(test2.addToSet(4));
		System.out.println(test2.addToSet(5));
		System.out.println(test2.addToSet(19));
		System.out.println(test2.addToSet(19));
		System.out.println(test2.addToSet(12));
		System.out.println(test2.addToSet(63));
		
		System.out.println("");
		
		test1.printSet();
		test2.printSet();
		
		System.out.println("");
		
		System.out.println(test1.isSetEmpty());
		System.out.println(test2.isSetEmpty());
		
		System.out.println("");
		
		System.out.println(test1.getCardinality());
		System.out.println(test2.getCardinality());
		
		System.out.println("");
		
		test1.intersection(test2).printSet();
		
		
	}
	/**
	 * initialise tests
	 */
	private void init() {
		test1 = new MySet();
		test2 = new MySet();
		
		testLottery = new Lottery();
	}

}
