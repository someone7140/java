package experiment;

public class StaticSample {

    public static void main(String[] args) {
        TestClass test = new TestClass();
        TestClass.updateSampleStatic("sample2");
        test.updateSample("sample1");
        System.out.println(TestClass.sample);

    }
}

class TestClass {
    public static String sample = "";

    public void updateSample(String sample){
        this.sample = sample;
    }

    public static void updateSampleStatic(String sample){
        TestClass.sample = sample;
    }
}
