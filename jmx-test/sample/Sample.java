package sample;

public class Sample {
    public void print(){
	System.out.println("sample.Sample print()");
	String packageName = this.getClass().getPackage().getName();
	String className = this.getClass().getName();
	System.out.println("responseStaticPage mehod invoked. Package:"+packageName+" Class:"+className);
    }
}
