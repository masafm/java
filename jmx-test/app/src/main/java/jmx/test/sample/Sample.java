package jmx.test.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sample {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public void print(){
	logger.info("sample.Sample print()");
	String packageName = this.getClass().getPackage().getName();
	String className = this.getClass().getName();
	logger.info("responseStaticPage mehod invoked. Package:"+packageName+" Class:"+className);
    }
}
