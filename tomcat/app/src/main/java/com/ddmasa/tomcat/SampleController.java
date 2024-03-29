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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    
    @Trace(operationName = "exception", resourceName = "com.ddmasa.tomcat.sample.SampleController")
    @RequestMapping("/exception")
    public String exception() throws Exception {
	logger.info("exception");
        throw new Exception();
    }
    
    @Trace(operationName = "500", resourceName = "com.ddmasa.tomcat.sample.SampleController")
    @RequestMapping("/500")
    public ResponseEntity<String> httpStatus500() {
	logger.info("http_status_500");
        return new ResponseEntity<>("Internal Server Error Message", HttpStatus.INTERNAL_SERVER_ERROR);
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
