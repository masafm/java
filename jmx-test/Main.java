import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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

		try {
		    // URLを作成してGET通信を行う
		    URL url = new URL("http://aws-agent.ddmasa.com:82/rewrite/test1/1234/?param=1234");
		    HttpURLConnection http = (HttpURLConnection)url.openConnection();
		    http.setRequestMethod("GET");
		    http.connect();
		    // サーバーからのレスポンスを標準出力へ出す
		    BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
		    String xml = "", line = "";
		    while((line = reader.readLine()) != null)
			xml += line;
		    //System.out.println(xml);
		    reader.close();
		} catch(Exception e) {
		    System.out.println("Exception!!!");
		}
		
		Thread.sleep(1000);
		for(int i=0; i<1000; i++) {
		    list.add(0,Integer.valueOf(i));
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
