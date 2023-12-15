package jmx.test;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import jmx.test.sample.Sample;
import datadog.trace.api.DDTags;
import datadog.trace.api.Trace;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;

class Main {
    private static ArrayList<Integer> list = new ArrayList<>();

    //@Trace(operationName = "main", resourceName = "jmx.test.Main.main")
    public static void main(String[] args) {
	System.out.println("main method(String[]).");
	Sample s = new Sample();
	Main m = new Main();
	while (true) {
	    try {
		
		m.myFunc();
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

		final Span span = GlobalTracer.get().activeSpan();
		if (span != null) {
		    System.out.println("Custom span tag");
		    int customer_id = 254889;
		    String customer_tier = "platinum";
		    int cart_value = 867;
		    span.setTag("customer.id", customer_id);
		    span.setTag("customer.tier", customer_tier);
		    span.setTag("cart.value", cart_value);
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
    
    public String myFunc() {
	System.out.println("myFunc() test");
	Main.myFunc2();
	return "myFunc";
    }

    private static void myFunc2() {
	System.out.println("myFunc2() test");	
    }
}
