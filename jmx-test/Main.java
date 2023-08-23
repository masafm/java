import java.util.ArrayList;
import sample.Sample;

class Main {
    private static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
	System.out.println("main method(String[]).");
	Sample s = new Sample();
	while (true) {
	    try {
		Main.myFunc();
		s.print();
		Thread.sleep(1000);
		for(int i=0; i<1000; i++) {
		    list.add(0,new Integer(i));
		}
		Thread.sleep(1000);
		for(int i=0; i<1000; i++) {
		    list.remove(0);
		}
	    } catch (InterruptedException e) {
		
	    }
	}
    }
    
    private static void myFunc() {
	System.out.println("myFunc() test");
	Main.myFunc2();
    }

    private static void myFunc2() {
	System.out.println("myFunc2() test");	
    }
}
