package jmx.test.sample;

// logback
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// log4j2
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

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

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/sb")
    public String helloSp() {
        return "Hello SpringBoot!";
    }
}
