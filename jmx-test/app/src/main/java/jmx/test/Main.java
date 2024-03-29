package jmx.test;

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import jmx.test.sample.Sample;
import datadog.trace.api.DDTags;
import datadog.trace.api.Trace;
import datadog.trace.api.interceptor.MutableSpan;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Main {
    private static final ArrayList<Integer> list = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String strUrl = null;
    
    public Main() {
    	strUrl = System.getProperty("target.url");

    	if (strUrl == null || strUrl.isEmpty()) {
	    strUrl = System.getenv("TARGET_URL");
	}
    	if (strUrl == null || strUrl.isEmpty()) {
	    strUrl = "http://localhost/";
    	}
    }
    
    public static void main(String[] args) {
	Main m = new Main();
	m.logger.info("main method(String[]).");
	while (true) {
	    try {
		m.print();
		m.exec();
		Thread.sleep(1000);
		for(int i=0; i<1000; i++) {
		    list.add(0,Integer.valueOf(i));
		}
		Thread.sleep(1000);
		for(int i=0; i<1000; i++) {
		    list.remove(0);
		}
	    } catch (InterruptedException e) {}
	}
    }

    @Trace(operationName = "httpAccess", resourceName = "jmx.test.Main")
    private void httpAccess(String strUrl) {
	try {
	    // Create URL and exec HTTP GET access
	    logger.info("Accessing "+strUrl);
	    URL url = new URL(strUrl);
	    HttpURLConnection http = (HttpURLConnection)url.openConnection();
	    http.setRequestMethod("GET");
	    http.connect();
	    // Wrtie http content to debug log
	    BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
	    String text = "", line = "";
	    while((line = reader.readLine()) != null)
		text += line;
	    logger.debug(text);
	    reader.close();
	} catch(Exception e) {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    String stackTrace = sw.toString();
	    logger.error(stackTrace);
	    //Set error on span
	    MutableSpan span = (MutableSpan)GlobalTracer.get().activeSpan();
	    span.setError(true);
	    MutableSpan localRootSpan = span.getLocalRootSpan();
	    localRootSpan.setError(true);
	}

    }
    
    @Trace(operationName = "exec", resourceName = "jmx.test.Main")
    private void exec() {
	Sample s = new Sample();
	s.print();	    

	httpAccess(strUrl);
	Span span = GlobalTracer.get().activeSpan();
	logger.info(span.toString());
	if (span != null) {
	    logger.info("Custom span tag");
	    int customer_id = 254889;
	    String customer_tier = "platinum";
	    int cart_value = 867;
	    span.setTag("customer.id", customer_id);
	    span.setTag("customer.tier", customer_tier);
	    span.setTag("cart.value", cart_value);
	}

    }
    
    @Trace(operationName = "print", resourceName = "jmx.test.Main")
    public String print() {
	logger.info("print() test");
	print2();
	return "myFunc";
    }

    @Trace(operationName = "print2", resourceName = "jmx.test.Main")
    private void print2() {
	logger.info("print2() test");	
    }
}
