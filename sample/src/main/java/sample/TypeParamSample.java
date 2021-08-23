package sample;

import java.util.function.Supplier;

class TypeParamTest<T> {
    public T tObject;
    TypeParamTest(Supplier<? extends T> ctor) {
        this.tObject = ctor.get();
    }
}

public class TypeParamSample {
    public static void main(String[] args) {
        TypeParamTest<String> test = new TypeParamTest(String::new);
        System.out.println(test.tObject.isEmpty());
    }
}
