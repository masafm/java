package com.ddmasa.tomcat;

// logback
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// log4j2
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import datadog.trace.api.DDTags;
import datadog.trace.api.Trace;
import datadog.trace.api.interceptor.MutableSpan;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    // logback
    private final Logger logger = LoggerFactory.getLogger(getClass());
    // log4j2
    //private final Logger logger = LogManager.getLogger();
    
    public void print(){
	logger.info("sample.Sample print()");
	String packageName = this.getClass().getPackage().getName();
	String className = this.getClass().getName();
	logger.info("Package:"+packageName+" Class:"+className);
    }

    @Trace(operationName = "home", resourceName = "com.ddmasa.tomcat.sample.SampleController")
    @RequestMapping("/")
    public String home() {
	logger.info("home");
	Main m = new Main();
	m.exec();
        return "Hello World!";
    }
    
    @Trace(operationName = "helloSp", resourceName = "com.ddmasa.tomcat.sample.SampleController")
    @RequestMapping("/sb")
    public String helloSp() {
	logger.info("helloSp");
	Main m = new Main();
	m.exec();
        return "Hello SpringBoot!";	
    }
}
