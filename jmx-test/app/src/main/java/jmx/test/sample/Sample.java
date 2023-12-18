package jmx.test.sample;

// logback
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// log4j2
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sample {
    // logback
    //private final Logger logger = LoggerFactory.getLogger(getClass());
    // log4j2
    private final Logger logger = LogManager.getLogger();
    
    public void print(){
	logger.info("sample.Sample print()");
	String packageName = this.getClass().getPackage().getName();
	String className = this.getClass().getName();
	logger.info("Package:"+packageName+" Class:"+className);
    }
}
